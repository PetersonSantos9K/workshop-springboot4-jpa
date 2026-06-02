package com.petersonexercicio.course.dto.response;

import java.math.BigDecimal;

public record ProductResponseDTO(Long id,String name, String description, BigDecimal price, String imgUrl) {}
