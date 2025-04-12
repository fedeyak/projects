package com.gmail.fedeyak.book_service.service;

import com.gmail.fedeyak.book_service.entity.Book;
import com.gmail.fedeyak.book_service.repository.BookRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setVendorCode("12345");
        book.setPublicationYear("2020");
        book.setBrand("TestBrand");
        book.setStock(10);
        book.setPrice(new BigDecimal("19.99"));
    }

    @Test
    public void testCreateBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book createdBook = bookService.createBook(book);

        assertNotNull(createdBook);
        assertEquals("Test Book", createdBook.getTitle());
        assertEquals(new BigDecimal("19.99"), createdBook.getPrice());
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    public void testGetBookById() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Optional<Book> fetchedBook = bookService.getBookById(1L);

        assertTrue(fetchedBook.isPresent());
        assertEquals("Test Book", fetchedBook.get().getTitle());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteBook() {
        when(bookRepository.existsById(1L)).thenReturn(true);

        boolean isDeleted = bookService.deleteBook(1L);

        assertTrue(isDeleted);
        verify(bookRepository, times(1)).deleteById(1L);
    }
}
