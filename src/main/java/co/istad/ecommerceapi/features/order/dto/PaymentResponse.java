package co.istad.ecommerceapi.features.order.dto;

import java.time.LocalDate;

public record PaymentResponse (
        String uuid,
        Boolean paymentStatus,
        LocalDate deliveryDate,
        Boolean deliveryOut
){
}
