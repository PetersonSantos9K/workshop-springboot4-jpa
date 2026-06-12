package com.petersonexercicio.course.dto.request.create;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
        @NotBlank(message = "Name is required")
        @Size(min=3, message = "Name must be at least 3 characters long")
        String name,
        @NotBlank(message = "Email is required")
        @Email(message="Invalid email format")
        String email,
        @NotBlank(message="Telephone is required")
        @Pattern(regexp = "^\\d{10,11}$", message = "Invalid telephone format")
        String telephone,

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        String password) {
}
