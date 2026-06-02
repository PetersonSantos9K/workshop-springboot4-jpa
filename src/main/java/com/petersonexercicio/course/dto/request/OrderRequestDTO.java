package com.petersonexercicio.course.dto.request;
import java.time.Instant;

public record OrderRequestDTO(Instant moment, Integer orderStatusCode, Long userId) {
}
