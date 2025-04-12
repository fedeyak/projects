package com.gmail.fedeyak.book_service.mapper;

import com.gmail.fedeyak.book_service.dto.BookRequest;
import com.gmail.fedeyak.book_service.dto.BookResponse;
import com.gmail.fedeyak.book_service.entity.Book;

public class BookMapper {

    public static Book toEntity(BookRequest request) {
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setVendorCode(request.getVendorCode());
        book.setPublicationYear(request.getPublicationYear());
        book.setBrand(request.getBrand());
        book.setStock(request.getStock());
        book.setPrice(request.getPrice());
        return book;
    }

    public static BookResponse toResponse(Book book) {
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

    public static BookRequest toRequest(Book book) {
        BookRequest request = new BookRequest();
        request.setTitle(book.getTitle());
        request.setVendorCode(book.getVendorCode());
        request.setPublicationYear(book.getPublicationYear());
        request.setBrand(book.getBrand());
        request.setStock(book.getStock());
        request.setPrice(book.getPrice());
        return request;
    }

    public static void updateEntity(Book book, BookRequest request) {
        book.setTitle(request.getTitle());
        book.setVendorCode(request.getVendorCode());
        book.setPublicationYear(request.getPublicationYear());
        book.setBrand(request.getBrand());
        book.setStock(request.getStock());
        book.setPrice(request.getPrice());
    }
}