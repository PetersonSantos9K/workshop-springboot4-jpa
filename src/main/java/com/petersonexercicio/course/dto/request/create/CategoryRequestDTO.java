package com.petersonexercicio.course.dto.request.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequestDTO(
        @NotBlank(message = "Name is required")
        @Size(min = 2, message = "Name must be at least 2 characters long")
        String name) {
}
