package com.petersonexercicio.course.dto.request.create;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrderItemRequestDTO(
    @NotNull(message = "Product ID is required")
    Long productId,
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be a positive integer")
    Integer quantity
) {
}
