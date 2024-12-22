package co.istad.ecommerceapi.features.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderRequest(
        @NotBlank(message = "UUID User must be not blank")
        String uuidUser,
        @NotBlank(message = "Product Name must be not blank")
        String name,
        @Positive
        @NotBlank(message = "Size must be not blank")
        String size,
        @NotBlank
        String description,
        @NotNull(message = "QTY must be not null")
        @Positive
        Integer qty
) {
}
