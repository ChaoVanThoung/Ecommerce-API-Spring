package co.istad.ecommerceapi.features.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @Email
        @NotBlank(message = "Email is request")
        String email,
        @NotBlank
        String password
) {
}
