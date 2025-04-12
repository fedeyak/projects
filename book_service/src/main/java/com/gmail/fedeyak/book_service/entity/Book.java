package com.gmail.fedeyak.book_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vendorCode;
    private String title;
    private String publicationYear;
    private String brand;
    private Integer stock;
    private BigDecimal price;

    public Book(String vendorCode, String title, String publicationYear, String brand, Integer stock, BigDecimal price) {
        this.vendorCode = vendorCode;
        this.title = title;
        this.publicationYear = publicationYear;
        this.brand = brand;
        this.stock = stock;
        this.price = price;
    }
}
