package co.istad.ecommerceapi.features.category.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        @NotBlank
        String categoryName
) {
}
