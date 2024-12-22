package co.istad.ecommerceapi.features.order;

import co.istad.ecommerceapi.features.order.dto.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/fromCustomer")
    public ResponseEntity<List<OrderFormCustomerResponse>> orderFormCustomer() {
        List<OrderFormCustomerResponse> responseList = orderService.oderFromCustomer();
        return ResponseEntity.ok(responseList);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PutMapping("/enableDelivery/{uuid}")
    PaymentResponse enableDelivery(@PathVariable String uuid) {
        return orderService.enableDelivery(uuid);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @GetMapping("/paid/findAll")
    List<PaymentResponse> findAllPaid(){
        return orderService.findAllPaid();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @PutMapping("/payment/{uuid}")
    PaymentResponse payment(@PathVariable("uuid") String uuid) {
        return orderService.payment(uuid);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @PostMapping("/update/{uuid}")
    OrderResponse updateByUuid(@PathVariable String uuid,@Valid @RequestBody OrderUpdateRequest orderUpdateRequest) {
        return orderService.updateByUuid(uuid,orderUpdateRequest);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @DeleteMapping("/delete/{uuid}")
    OrderResponse deleteByUuid(@PathVariable String uuid) {
        return orderService.deleteByUuid(uuid);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @PostMapping("/addProduct")
    OrderResponse addProduct(@Valid @RequestBody OrderRequest orderRequest) {
        return orderService.addProduct(orderRequest);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @GetMapping("/findAll")
    List<OrderResponse> findAll() {
        return orderService.findAll();
    }
}
