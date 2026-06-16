package com.petersonexercicio.course.dto.request.create;
import jakarta.validation.constraints.*;

import java.time.Instant;
import java.util.List;

public record OrderRequestDTO(
        @NotNull(message = "Date is required")
        @PastOrPresent(message = "Date must be in the past or present")
        Instant moment,
        @NotNull(message = "Status is required")
        @Positive(message = "Status must be a positive integer")
        Integer status,
        @NotNull(message = "User ID is required")
        @Positive(message = "User ID must be a positive integer")
        Long userId,

        @NotEmpty(message = "List must not be empty")
        @NotNull(message = "Items list is required")
        List<OrderItemRequestDTO> items
){
}
