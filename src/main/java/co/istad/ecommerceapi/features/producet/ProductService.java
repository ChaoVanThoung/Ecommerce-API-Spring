package co.istad.ecommerceapi.features.producet;

import co.istad.ecommerceapi.features.producet.dto.ProductResponse;
import co.istad.ecommerceapi.features.producet.dto.ProductUpdateRequest;
import co.istad.ecommerceapi.features.producet.dto.ProductUploadRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    ProductResponse updateByName(String name, ProductUpdateRequest productUpdateRequest);

    ProductResponse deleteByName(String name);

    ProductResponse uploadNewProduce(ProductUploadRequest productUpload);

    List<ProductResponse> searchByName(String name);

    ProductResponse findByName(String name);

    Page<ProductResponse> findAll(int pageNo, int pageSize);
}
