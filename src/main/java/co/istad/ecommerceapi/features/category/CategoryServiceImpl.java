package co.istad.ecommerceapi.features.category;

import co.istad.ecommerceapi.domain.Category;
import co.istad.ecommerceapi.domain.Product;
import co.istad.ecommerceapi.features.category.dto.CategoryRequest;
import co.istad.ecommerceapi.features.category.dto.CategoryResponse;
import co.istad.ecommerceapi.features.producet.ProductRepository;
import co.istad.ecommerceapi.features.producet.dto.ProductResponse;
import co.istad.ecommerceapi.mapper.CategoryMapper;
import co.istad.ecommerceapi.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CategoryMapper categoryMapper;
    private final ProductMapper productMapper;

    @Override
    public List<ProductResponse> findByName(String name) {
        Category category = categoryRepository.findByCategoryName(name)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Category not found"
                ));
        List<Product> products = productRepository.findByCategory(category);
        if (products.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No products found");
        }
        return productMapper.toProductListResponse(products);
    }

    @Override
    public CategoryResponse deleteByCategoryName(String name) {
        Category category = categoryRepository
                .findByCategoryName(name)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category not found"));
        categoryRepository.delete(category);
        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public List<CategoryResponse> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toCategoryListResponse(categories);
    }

    @Override
    public CategoryResponse addCategory(CategoryRequest categoryRequest) {

        if (categoryRepository.existsByCategoryName(categoryRequest.categoryName())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Category already exists"
            );
        }

        Category category = categoryMapper.fromAddCategory(categoryRequest);
        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.toCategoryResponse(savedCategory) ;
    }
}
