package co.istad.ecommerceapi.features.order.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record OrderUpdateRequest(
        @Positive
        @Min(1)
        @Max(100)
        Integer qty
) {
}