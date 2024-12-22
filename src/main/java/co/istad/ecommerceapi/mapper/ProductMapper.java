package co.istad.ecommerceapi.mapper;

import co.istad.ecommerceapi.domain.Product;
import co.istad.ecommerceapi.features.producet.dto.ProductResponse;
import co.istad.ecommerceapi.features.producet.dto.ProductUpdateRequest;
import co.istad.ecommerceapi.features.producet.dto.ProductUploadRequest;
import org.mapstruct.*;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ProductMapper {

    List<ProductResponse> toProductListResponse(List<Product> products);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUpdateProductRequestPartially(ProductUpdateRequest productUpdateRequest,
                                           @MappingTarget Product product);

    Product fromProductUploadRequest(ProductUploadRequest productUploadRequest);

    @Mapping(target = "categoryName", expression = "java(product.getCategory() != null ? product.getCategory().getCategoryName() : null)")
    ProductResponse toProduceResponse(Product product);

}
