package co.istad.ecommerceapi.features.producet.dto;

import co.istad.ecommerceapi.domain.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public record ProductUploadRequest(
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
