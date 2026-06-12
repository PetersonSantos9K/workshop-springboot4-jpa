package com.petersonexercicio.course.dto.response;

import java.math.BigDecimal;

public record OrderItemResponseDTO(
        Long id,
        ProductResponseDTO product,
        Integer quantity,
        BigDecimal price,
        BigDecimal subTotal
) {
}
