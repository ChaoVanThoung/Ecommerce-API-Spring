package co.istad.ecommerceapi.features.category;

import co.istad.ecommerceapi.features.category.dto.CategoryRequest;
import co.istad.ecommerceapi.features.category.dto.CategoryResponse;
import co.istad.ecommerceapi.features.producet.dto.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/search/{name}")
    List<ProductResponse> searchByName(@PathVariable String name) {
        return categoryService.findByName(name);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')" )
    @DeleteMapping("/delete/{name}")
    CategoryResponse deleteCategory(@Valid @PathVariable String name) {
        return categoryService.deleteByCategoryName(name);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')" )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    CategoryResponse addCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        return categoryService.addCategory(categoryRequest);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @GetMapping
    List<CategoryResponse> findAll() {
        return categoryService.findAll();
    }
}
