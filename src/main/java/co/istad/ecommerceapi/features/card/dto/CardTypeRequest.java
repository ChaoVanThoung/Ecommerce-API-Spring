package co.istad.ecommerceapi.features.card.dto;

import jakarta.validation.constraints.NotBlank;

public record CardTypeRequest(
        @NotBlank
        String cardTypeName
) {
}
