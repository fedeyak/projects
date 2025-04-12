package com.gmail.fedeyak.book_service.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private Long id;
    private String title;
    private String vendorCode;
    private String publicationYear;
    private String brand;
    private int stock;
    private BigDecimal price;
}
