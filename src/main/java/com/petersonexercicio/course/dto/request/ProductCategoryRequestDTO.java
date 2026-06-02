package com.petersonexercicio.course.dto.request;

import java.util.Set;

public record ProductCategoryRequestDTO(Set<Long> categoryIds) {
}
