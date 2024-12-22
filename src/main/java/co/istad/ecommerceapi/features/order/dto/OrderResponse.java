package co.istad.ecommerceapi.features.order.dto;

import co.istad.ecommerceapi.features.producet.dto.ProductResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        String uuid,
        String name,
        String size,
        String description,
        Integer qty,
        BigDecimal price,
        BigDecimal totalPrice,
        Boolean paymentStatus,
        LocalDate orderDate

//        List<ProductResponse> products
) {
}
