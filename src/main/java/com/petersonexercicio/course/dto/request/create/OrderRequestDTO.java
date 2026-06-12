package com.petersonexercicio.course.dto.request.create;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.Instant;

public record OrderRequestDTO(
        @NotBlank(message = "Date is required")
        @PastOrPresent(message = "Date must be in the past or present")
        Instant moment,
        @NotNull(message = "Status is required")
        @Positive(message = "Status must be a positive integer")
        Integer status,
        @NotNull(message = "User ID is required")
        @Positive(message = "User ID must be a positive integer")
        Long userId) {
}
