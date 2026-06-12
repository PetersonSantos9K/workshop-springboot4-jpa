package com.petersonexercicio.course.dto.request.create;

public record OrderItemRequestDTO(
    Long productId,
    Integer quantity
) {
}
