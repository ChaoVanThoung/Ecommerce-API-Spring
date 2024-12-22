package co.istad.ecommerceapi.mapper;

import co.istad.ecommerceapi.domain.Category;
import co.istad.ecommerceapi.features.category.dto.CategoryRequest;
import co.istad.ecommerceapi.features.category.dto.CategoryResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category fromAddCategory(CategoryRequest addCategoryRequest);

    CategoryResponse toCategoryResponse(Category category);

    List<CategoryResponse> toCategoryListResponse(List<Category> categories);
}
