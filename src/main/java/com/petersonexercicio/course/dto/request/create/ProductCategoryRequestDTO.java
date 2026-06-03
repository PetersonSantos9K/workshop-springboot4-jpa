package com.petersonexercicio.course.dto.request.create;

import java.util.Set;

public record ProductCategoryRequestDTO(Set<Long> categoryIds) {
}
