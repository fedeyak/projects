package com.gmail.fedeyak.book_service.repository;

import com.gmail.fedeyak.book_service.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("""
                SELECT b FROM Book b
                WHERE (:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%')))
                AND (:year IS NULL OR b.publicationYear = :year)
                AND (:brand IS NULL OR LOWER(b.brand) LIKE LOWER(CONCAT('%', :brand, '%')))

            """)
    Page<Book> searchBooks(
            @Param("title") String title,
            @Param("year") Integer publicationYear,
            @Param("brand") String brand,
            Pageable pageable
    );

}

