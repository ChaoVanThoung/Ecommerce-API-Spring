package co.istad.ecommerceapi.features.producet.dto;

import java.math.BigDecimal;

public record ProductResponse(
        String productName,
        BigDecimal price,
        String size,
        boolean available,
        Integer stock,
        Integer numberOfPeopleBought,
        String categoryName
) {
}
