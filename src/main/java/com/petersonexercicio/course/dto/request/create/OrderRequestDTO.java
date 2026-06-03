package com.petersonexercicio.course.dto.request.create;
import java.time.Instant;

public record OrderRequestDTO(Instant moment, Integer status, Long userId) {
}
