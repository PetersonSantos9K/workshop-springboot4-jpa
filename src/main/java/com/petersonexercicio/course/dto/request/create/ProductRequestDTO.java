package com.petersonexercicio.course.dto.request.create;

import java.math.BigDecimal;

public record ProductRequestDTO(String name, String description, BigDecimal price, String imgUrl) {}
