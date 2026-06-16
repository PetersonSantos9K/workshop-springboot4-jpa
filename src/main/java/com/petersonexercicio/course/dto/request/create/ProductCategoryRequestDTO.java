package com.petersonexercicio.course.dto.request.create;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record ProductCategoryRequestDTO(
        @NotEmpty(message = "Set must not be empty")
        @NotNull(message = "Category Ids is required")
        Set<Long> categoryIds) {
}
