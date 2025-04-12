


package com.gmail.fedeyak.book_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.fedeyak.book_service.dto.BookRequest;
import com.gmail.fedeyak.book_service.entity.Book;
import com.gmail.fedeyak.book_service.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private BookRequest validRequest;

    @BeforeEach
    public void setup() {
        validRequest = new BookRequest();
        validRequest.setTitle("Clean Code");
        validRequest.setVendorCode("123ABC");
        validRequest.setPublicationYear("2008");
        validRequest.setBrand("Pearson");
        validRequest.setStock(10);
        validRequest.setPrice(BigDecimal.valueOf(29.99));
    }

    @Test
    public void testCreateBook_Success() throws Exception {
        String json = objectMapper.writeValueAsString(validRequest);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("Clean Code"));
    }

    @Test
    public void testCreateBook_ValidationError() throws Exception {
        BookRequest invalidRequest = new BookRequest(); // пустой
        String json = objectMapper.writeValueAsString(invalidRequest);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").doesNotExist());
    }

    @Test
    public void testGetAllBooks() throws Exception {
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetBookById_NotFound() throws Exception {
        mockMvc.perform(get("/api/books/999999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateBook_Success() throws Exception {
        // Сначала создаём книгу
        String json = objectMapper.writeValueAsString(validRequest);
        MvcResult result = mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        Long id = objectMapper.readTree(responseBody).get("id").asLong();

        // Меняем название
        validRequest.setTitle("Refactoring");
        String updatedJson = objectMapper.writeValueAsString(validRequest);

        mockMvc.perform(put("/api/books/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Refactoring"));
    }

    @Test
    public void testDeleteBook_Success() throws Exception {
        // Сначала создаём книгу
        String json = objectMapper.writeValueAsString(validRequest);
        MvcResult result = mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn();

        Long id = objectMapper.readTree(result.getResponse().getContentAsString()).get("id").asLong();

        // Удаляем
        mockMvc.perform(delete("/api/books/" + id))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteBook_NotFound() throws Exception {
        mockMvc.perform(delete("/api/books/999999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetBooksWithPagination() throws Exception {
        mockMvc.perform(get("/api/books?page=0&size=5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", lessThanOrEqualTo(5)));
    }

//    @Test
//    public void testGetBooksWithPagination() throws Exception {
//        mockMvc.perform(get("/api/books?page=0&size=10"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(10)));  // Проверка, что вернуто 10 книг
//    }

    @Test
    public void testGetBooksWithFilters() throws Exception {
        mockMvc.perform(get("/api/books?page=0&size=10&title=Java&publicationYear=2020"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", containsString("Java")))
                .andExpect(jsonPath("$[0].publicationYear", equalTo("2020")));
    }

}

//
//
//@WebMvcTest(BookController.class)
//public class BookControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private BookService bookService;
//
//    private Book book;
//
//    @BeforeEach
//    public void setUp() {
//        book = new Book();
//        book.setId(1L);
//        book.setTitle("Test Book");
//        book.setVendorCode("12345");
//        book.setPublicationYear("2020");
//        book.setBrand("TestBrand");
//        book.setStock(10);
//        book.setPrice(new BigDecimal("19.99"));
//    }
//
//    @Test
//    public void testGetAllBooks() throws Exception {
//        when(bookService.getAllBooks()).thenReturn(List.of(book));
//
//        mockMvc.perform(get("/api/books"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].title").value("Test Book"));
//    }
//
//    @Test
//    public void testGetBookById_Found() throws Exception {
//        when(bookService.getBookById(1L)).thenReturn(Optional.of(book));
//
//        mockMvc.perform(get("/api/books/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.title").value("Test Book"));
//    }
//
//    @Test
//    public void testGetBookById_NotFound() throws Exception {
//        when(bookService.getBookById(1L)).thenReturn(Optional.empty());
//
//        mockMvc.perform(get("/api/books/1"))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void testCreateBook_Success() throws Exception {
//        when(bookService.createBook(any(Book.class))).thenReturn(book);
//
//        mockMvc.perform(post("/api/books")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{ \"title\": \"Test Book\", \"vendorCode\": \"12345\", \"publicationYear\": \"2020\", \"brand\": \"TestBrand\", \"stock\": 10, \"price\": 19.99 }"))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.title").value("Test Book"));
//    }
//
//    @Test
//    public void testUpdateBook_Success() throws Exception {
//        when(bookService.updateBook(eq(1L), any(Book.class))).thenReturn(book);
//
//        mockMvc.perform(put("/api/books/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{ \"title\": \"Updated Title\", \"vendorCode\": \"12345\", \"publicationYear\": \"2020\", \"brand\": \"TestBrand\", \"stock\": 15, \"price\": 29.99 }"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.title").value("Test Book")); // возвращается старый мок
//    }
//
//    @Test
//    public void testUpdateBook_NotFound() throws Exception {
//        when(bookService.updateBook(eq(1L), any(Book.class))).thenReturn(null);
//
//        mockMvc.perform(put("/api/books/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{ \"title\": \"Updated Title\", \"vendorCode\": \"12345\", \"publicationYear\": \"2020\", \"brand\": \"TestBrand\", \"stock\": 15, \"price\": 29.99 }"))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void testDeleteBook() throws Exception {
//        when(bookService.deleteBook(1L)).thenReturn(true);
//
//        mockMvc.perform(delete("/api/books/1"))
//                .andExpect(status().isNoContent());
//    }
//}
