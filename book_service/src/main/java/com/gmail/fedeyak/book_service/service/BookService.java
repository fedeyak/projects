package com.gmail.fedeyak.book_service.service;

import com.gmail.fedeyak.book_service.entity.Book;
import com.gmail.fedeyak.book_service.dto.BookRequest;
import com.gmail.fedeyak.book_service.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Page<Book> getAllBooks(String title, String publicationYear, String brand, Pageable pageable) {
        Integer year = null;
        if (publicationYear != null && !publicationYear.isBlank()) {
            try {
                year = Integer.parseInt(publicationYear);
            } catch (NumberFormatException e) {
                year = null;
            }
        }

        return bookRepository.searchBooks(title, year, brand, pageable);
    }

    // Создание новой книги
    public void createBook(BookRequest bookRequest) {
        Book book = new Book(
                null, // id будет сгенерирован автоматически
                bookRequest.getVendorCode(),
                bookRequest.getTitle(),
                bookRequest.getPublicationYear(),
                bookRequest.getBrand(),
                bookRequest.getStock(),
                bookRequest.getPrice()
        );
        bookRepository.save(book);
    }

    // Создание книги по сущности (используется в REST-контроллере)
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> updateBook(Long id, BookRequest bookRequest) {
        System.out.println(">>> Updating book: " + bookRequest);
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            book.setTitle(bookRequest.getTitle());
            book.setVendorCode(bookRequest.getVendorCode());
            book.setPublicationYear(bookRequest.getPublicationYear());
            book.setBrand(bookRequest.getBrand());
            book.setStock(bookRequest.getStock());
            book.setPrice(bookRequest.getPrice());
            bookRepository.save(book);
            System.out.println(">>> Updating book: " + bookRequest);
            return Optional.of(book);
        }
        System.out.println(">>> Updating book: " + bookRequest);
        return Optional.empty();
    }

    // Получение книги по id
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

}