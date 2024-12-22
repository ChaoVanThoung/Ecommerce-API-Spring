package co.istad.ecommerceapi.features.category;

import co.istad.ecommerceapi.features.category.dto.CategoryRequest;
import co.istad.ecommerceapi.features.category.dto.CategoryResponse;
import co.istad.ecommerceapi.features.producet.dto.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    List<ProductResponse> findByName(String name);

    CategoryResponse deleteByCategoryName(String name);

    List<CategoryResponse> findAll();

    CategoryResponse addCategory(CategoryRequest categoryRequest);
}
