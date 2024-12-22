package co.istad.ecommerceapi.mapper;

import co.istad.ecommerceapi.domain.Order;
import co.istad.ecommerceapi.domain.User;
import co.istad.ecommerceapi.features.order.dto.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {

//    @Mapping(target = "userName", source = "user.name", defaultValue = "Unknown User")
//    @Mapping(target = "price", source = "product.price", defaultExpression = "java(order.getProduct() != null && order.getProduct().getPrice() != null ? order.getProduct().getPrice() : BigDecimal.ZERO)")
//    @Mapping(target = "CityOrProvince", source = "user.CityOrProvince", defaultValue = "Unknown City/Province")
//    @Mapping(target = "KhanOrDistrict", source = "user.KhanOrDistrict", defaultValue = "Unknown District")
//    @Mapping(target = "village", source = "user.village", defaultValue = "Unknown Village")
//    @Mapping(target = "street", source = "user.street", defaultValue = "Unknown Street")
//    List<OrderFormCustomerResponse> toOrderFormCustomerResponse(List<Order> orders);

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mappings({
            @Mapping(target = "userName", source = "user.name", defaultValue = "Unknown User"),
            @Mapping(target = "price", source = "product.price",
                    defaultExpression = "java((order.getProduct() != null && order.getProduct().getPrice() != null) ? order.getProduct().getPrice() : BigDecimal.ZERO)"),
            @Mapping(target = "CityOrProvince", source = "user.cityOrProvince", defaultValue = "Unknown City/Province"),
            @Mapping(target = "KhanOrDistrict", source = "user.khanOrDistrict", defaultValue = "Unknown District"),
            @Mapping(target = "village", source = "user.village", defaultValue = "Unknown Village"),
            @Mapping(target = "street", source = "user.street", defaultValue = "Unknown Street")
    })
    OrderFormCustomerResponse toOrderFormCustomerResponse(Order order);


    @Mapping(target = "price", source = "product.price")
    List<PaymentResponse> toPaymentListResponse(List<Order> orders);

    PaymentResponse toPaymentResponse(Order order);

    List<OrderResponse> toOrderListResponse(List<Order> orders);


    Order fromOrderResponse(OrderRequest orderRequest);

    @Mapping(target = "price", source = "product.price")
    OrderResponse toOrderResponse(Order order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromOrderUpdateRequestPartially(OrderUpdateRequest orderUpdateRequest,
                                         @MappingTarget Order order);
}
