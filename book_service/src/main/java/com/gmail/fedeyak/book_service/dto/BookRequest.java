package com.gmail.fedeyak.book_service.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {

    @NotBlank(message = "Vendor code is mandatory")
    private String vendorCode;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotNull(message = "Year is required")
    @Pattern(
            regexp = "^(18|19|20)\\d{2}$",
            message = "Year must be 4 digits and start with 18, 19, or 20"
    )
    private String publicationYear;

    @NotBlank(message = "Brand is mandatory")
    private String brand;

    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock must be non-negative")
    private Integer stock;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be positive")
    private BigDecimal price;
}