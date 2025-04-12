package com.gmail.fedeyak.book_service.repository;

import com.gmail.fedeyak.book_service.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setTitle("Test Book");
        book.setVendorCode("12345");
        book.setPublicationYear("2020");
        book.setBrand("TestBrand");
        book.setStock(10);
        book.setPrice(new BigDecimal("19.99"));
        bookRepository.save(book);
    }

    @Test
    public void testFindById() {
        Optional<Book> foundBook = bookRepository.findById(book.getId());

        assertTrue(foundBook.isPresent());
        assertEquals("Test Book", foundBook.get().getTitle());
    }

    @Test
    public void testFindAll() {
        assertTrue(bookRepository.findAll().size() > 0);
    }
}
