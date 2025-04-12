package com.gmail.fedeyak.book_service.controller;

import com.gmail.fedeyak.book_service.dto.BookRequest;
import com.gmail.fedeyak.book_service.dto.BookResponse;
import com.gmail.fedeyak.book_service.entity.Book;
import com.gmail.fedeyak.book_service.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookResponse>> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String publicationYear, // 🔧 БЫЛО: author
            @RequestParam(required = false) String brand
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> bookPage = bookService.getAllBooks(title, publicationYear, brand, pageable);

        List<BookResponse> bookResponses = bookPage.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(bookResponses);
    }



    // Получение книги по ID
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Создание новой книги
    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookRequest request) {
        Book book = toEntity(request);
        Book savedBook = bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(savedBook));
    }

    // Обновление информации о книге
    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequest request) {
        Optional<Book> updatedBook = bookService.updateBook(id, request);
        return updatedBook.map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Удаление книги по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        boolean isDeleted = bookService.deleteBook(id);
        return isDeleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    private BookResponse toResponse(Book book) {
        BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setVendorCode(book.getVendorCode());
        response.setPublicationYear(book.getPublicationYear());
        response.setBrand(book.getBrand());
        response.setStock(book.getStock());
        response.setPrice(book.getPrice());
        return response;
    }

    // Маппинг BookRequest (DTO) в сущность Book
    private Book toEntity(BookRequest request) {
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setVendorCode(request.getVendorCode());
        book.setPublicationYear(request.getPublicationYear());
        book.setBrand(request.getBrand());
        book.setStock(request.getStock());
        book.setPrice(request.getPrice());
        return book;
    }
}
