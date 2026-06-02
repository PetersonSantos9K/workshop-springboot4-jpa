package com.petersonexercicio.course.dto.response;

import java.util.Set;

public record UserResponseDTO(Long id, String name, String email, String telephone, Set<OrderResponseDTO> orders) {
}
