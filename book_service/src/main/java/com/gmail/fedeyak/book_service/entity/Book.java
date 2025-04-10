package com.gmail.fedeyak.book_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Vendor code is required")
    private String vendorCode;

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Year is required")
    @Pattern(
            regexp = "^(18|19|20)\\d{2}$",
            message = "Year must be 4 digits and start with 18, 19, or 20"
    )
    private String publicationYear;

    @NotBlank(message = "Brand is required")
    private String brand;

    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock must be >= 0")
    private Integer stock;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.00", message = "Price must be greater than or equal to 0")
    private BigDecimal price;

    public void setPrice(BigDecimal price) {
        if (price == null) {
            throw new IllegalArgumentException("Price must not be null");
        }
        this.price = price.setScale(2, RoundingMode.HALF_UP);
    }
}

