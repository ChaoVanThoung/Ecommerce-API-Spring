package co.istad.ecommerceapi.features.producet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductUpdateRequest(
        @NotBlank
        @Size(min = 1, max = 150)
        String productName,
        @NotNull
        BigDecimal price,
        String size,
        @NotBlank
        @Size(min = 1)
        String stock,
        String imageUrl,
        @NotBlank
        String categoryName
) {
}
