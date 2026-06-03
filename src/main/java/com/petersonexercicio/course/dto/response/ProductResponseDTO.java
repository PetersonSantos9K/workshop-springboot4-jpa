package com.petersonexercicio.course.dto.response;

import java.math.BigDecimal;
import java.util.Set;

public record ProductResponseDTO(Long id,String name, String description, BigDecimal price, String imgUrl, Set<CategoryResponseDTO> categories) {}
