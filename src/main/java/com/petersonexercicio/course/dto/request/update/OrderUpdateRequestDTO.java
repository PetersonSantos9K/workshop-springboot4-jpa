package com.petersonexercicio.course.dto.request.update;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderUpdateRequestDTO (
        @NotNull(message = "Status is required")
        @Positive(message = "Status must be a positive integer")
        Integer status) {
}
