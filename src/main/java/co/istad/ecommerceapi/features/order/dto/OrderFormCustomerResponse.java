package co.istad.ecommerceapi.features.order.dto;

import java.math.BigDecimal;

public record OrderFormCustomerResponse(
        String userName,
        String name,
        String size,
        String description,
        Integer qty,
        BigDecimal price,
        BigDecimal totalPrice,
        Boolean paymentStatus,

        String CityOrProvince,
        String KhanOrDistrict,
        String village,
        String street


) {
}
