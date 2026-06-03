package com.petersonexercicio.course.dto.response;

import com.petersonexercicio.course.entities.OrderItem;
import com.petersonexercicio.course.entities.Payment;
import com.petersonexercicio.course.entities.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

public record OrderResponseDTO(
        Long id, Instant moment,
        OrderStatus status, Payment payment,
        Set<OrderItem> items, BigDecimal total) {
}
