package co.istad.ecommerceapi.features.card.dto;

import java.time.LocalDate;

public record CardResponse(
        String cvv,
        String number,
        LocalDate expiryDate,
        String cardTypeName
) {
}
