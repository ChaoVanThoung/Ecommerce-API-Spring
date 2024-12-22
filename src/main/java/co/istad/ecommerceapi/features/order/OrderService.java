package co.istad.ecommerceapi.features.order;

import co.istad.ecommerceapi.features.order.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {


    List<OrderFormCustomerResponse> oderFromCustomer();

    PaymentResponse enableDelivery(String uuid);

    List<PaymentResponse> findAllPaid();

    PaymentResponse payment(String uuid);

    OrderResponse updateByUuid(String uuid, OrderUpdateRequest orderUpdateRequest);

    OrderResponse deleteByUuid(String uuid);

    List<OrderResponse> findAll();

    OrderResponse addProduct(OrderRequest orderRequest);
}
