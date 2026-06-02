package com.petersonexercicio.course.dto.request;

import java.math.BigDecimal;

public record ProductRequestDTO(String name, String description, BigDecimal price, String imgUrl) {}
