package com.gmail.fedeyak.book_service.web;

import com.gmail.fedeyak.book_service.dto.BookRequest;
import com.gmail.fedeyak.book_service.entity.Book;
import com.gmail.fedeyak.book_service.mapper.BookMapper;
import com.gmail.fedeyak.book_service.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class WebController {

    private final BookService bookService;

    // Отображение списка книг
    @GetMapping("/books")
    public String showBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String publicationYear,
            @RequestParam(required = false) String brand,
            Model model) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("title").ascending());
        Page<Book> bookPage = bookService.getAllBooks(title, publicationYear, brand, pageable);

        model.addAttribute("bookPage", bookPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("titleFilter", title);
        model.addAttribute("yearFilter", publicationYear);
        model.addAttribute("brandFilter", brand);

        return "books";
    }

    // Страница для добавления книги
    @GetMapping("/books/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("bookRequest", new BookRequest());
        return "book-form";
    }

    // Добавление новой книги
    @PostMapping("/books/add")
    public String addBook(@Valid @ModelAttribute BookRequest bookRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "book-form";
        }

        bookService.createBook(bookRequest);
        return "redirect:/books";
    }

    // Страница для редактирования книги
    @GetMapping("/books/edit/{id}")
    public String showEditBookForm(@PathVariable Long id, Model model) {
        Optional<Book> bookOpt = bookService.getBookById(id);
        if (bookOpt.isEmpty()) {
            return "redirect:/books";
        }

        Book book = bookOpt.get();
        BookRequest bookRequest = BookMapper.toRequest(book);

        model.addAttribute("bookRequest", bookRequest);
        model.addAttribute("bookId", id);
        return "book-form";
    }

    // Обновление книги
    @PostMapping("/books/edit/{id}")
    public String editBook(@PathVariable Long id, @Valid @ModelAttribute BookRequest bookRequest,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bookId", id); // обязательно снова передаём bookId, иначе форма не будет работать
            return "book-form";
        }

        Optional<Book> existingBook = bookService.getBookById(id);
        if (existingBook.isEmpty()) {
            return "redirect:/books";
        }

        bookService.updateBook(id, bookRequest);
        return "redirect:/books";
    }

    // Удаление книги
    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}

