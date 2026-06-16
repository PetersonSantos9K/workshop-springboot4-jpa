package com.petersonexercicio.course.dto.response;

import java.math.BigDecimal;

public record OrderItemResponseDTO(
        Long productId,
        Integer quantity,
        BigDecimal price,
        BigDecimal subTotal
) {
}
