package com.petersonexercicio.course.dto.request.create;

import jakarta.validation.constraints.NotEmpty;
import java.util.Set;

public record ProductCategoryRequestDTO(
        @NotEmpty(message = "Set must not be empty")
        Set<Long> categoryIds) {
}
