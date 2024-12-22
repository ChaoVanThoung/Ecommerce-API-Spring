package co.istad.ecommerceapi.features.order;

import co.istad.ecommerceapi.domain.Order;
import co.istad.ecommerceapi.domain.Product;
import co.istad.ecommerceapi.domain.User;
import co.istad.ecommerceapi.features.order.dto.*;
import co.istad.ecommerceapi.features.producet.ProductRepository;
import co.istad.ecommerceapi.features.user.UserRepository;
import co.istad.ecommerceapi.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;


    @Override
    public List<OrderFormCustomerResponse> oderFromCustomer() {
        List<Order> orders = orderRepository.findByPaymentStatusTrue();

        // Map orders to OrderFormCustomerResponse using MapStruct
        return orders.stream()
                .map(OrderMapper.INSTANCE::toOrderFormCustomerResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentResponse enableDelivery(String uuid) {
        Order order = orderRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Order not found"
                ));
        order.setDeliveryOut(true);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toPaymentResponse(savedOrder);
    }

    @Override
    public List<PaymentResponse> findAllPaid() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.toPaymentListResponse(orders);
    }

    @Override
    public PaymentResponse payment(String uuid) {
        Order order = orderRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Order not found"
                ));

        Product product = productRepository.findByProductName(order.getName())
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Product not found"
                        ));
        if (product.getStock() < order.getQty()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Product has not enough stock"
            );
        }

        product.setStock(product.getStock() - order.getQty());
        product.setNumberOfPeopleBought(product.getNumberOfPeopleBought() + 1);
        productRepository.save(product);

        order.setPaymentStatus(true);
        order.setDeliveryDate(LocalDate.now().plusDays(1));
        order.setDeliveryOut(false);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toPaymentResponse(savedOrder);
    }

    @Override
    public OrderResponse updateByUuid(String uuid, OrderUpdateRequest orderUpdateRequest) {

        Order order = orderRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Order not found"
                ));


        orderMapper.fromOrderUpdateRequestPartially(orderUpdateRequest, order);
        order.setTotalPrice(order.getProduct().getPrice().multiply(BigDecimal.valueOf(order.getQty())));
        Order savedOrder = orderRepository.save(order);

        return orderMapper.toOrderResponse(savedOrder);
    }

    @Override
    public OrderResponse deleteByUuid(String uuid) {

        Order order = orderRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Order not found"
                ));
        orderRepository.delete(order);

        return orderMapper.toOrderResponse(order);
    }

    @Override
    public List<OrderResponse> findAll() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.toOrderListResponse(orders);
    }

    @Override
    public OrderResponse addProduct(OrderRequest orderRequest) {

        Product product = productRepository.findByProductNameAndSize(orderRequest.name(),orderRequest.size())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Product Not Found"
                ));

        User user = userRepository.findByUuid(orderRequest.uuidUser())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User Not Found"
                ));

        Order order = orderMapper.fromOrderResponse(orderRequest);
        order.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(order.getQty())));
        order.setOrderDate(LocalDate.now());
        order.setPaymentStatus(false);
        order.setUuid(UUID.randomUUID().toString());
        order.setProduct(product);
        order.setUser(user);

        Order savedOrder = orderRepository.save(order);

        return orderMapper.toOrderResponse(savedOrder);
    }
}
