package co.istad.ecommerceapi.features.card.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record CardRequest(
        @NotBlank(message = "CVV must be not blank")
        @Positive(message = "Number CVV is positive")
        String cvv,
        @NotBlank(message = "number must be not blank")
        String number,
        @NotNull(message = "expiryDate must be not null")
        LocalDate expiryDate,
        @NotBlank(message = "cardTypeName bust be not blank")
        String cardTypeName
) {
}
