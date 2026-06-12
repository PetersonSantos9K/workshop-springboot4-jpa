package com.petersonexercicio.course.dto.request.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProductUpdateRequestDTO(
        @NotBlank(message = "Name is required")
        @Size(min = 3, message = "Name must be at least 3 characters long")
        String name,
        @NotBlank(message = "Description is required")
        @Size(min = 3, message = "Description must be at least 3 characters long")
        String description) {}
