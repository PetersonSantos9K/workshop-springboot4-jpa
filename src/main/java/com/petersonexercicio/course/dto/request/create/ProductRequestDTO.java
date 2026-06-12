package com.petersonexercicio.course.dto.request.create;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequestDTO(
        @NotBlank(message = "Name is required")
        @Size(min=3, message = "Name must be at least 3 characters long")
        String name,
        @NotBlank(message = "Description is required")
        String description,
        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
        @Digits(integer = 10, fraction = 2, message = "Invalid price format")
        BigDecimal price,
        @NotBlank(message="ImgUrl is required")
        String imgUrl
        )
{}
