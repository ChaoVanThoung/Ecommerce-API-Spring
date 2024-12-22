package co.istad.ecommerceapi.features.producet;

import co.istad.ecommerceapi.features.producet.dto.ProductUpdateRequest;
import co.istad.ecommerceapi.features.producet.dto.ProductUploadRequest;
import co.istad.ecommerceapi.features.producet.dto.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor

public class ProductController {

    private final ProductService productService;


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PatchMapping("/Update/{name}")
    ProductResponse updateByName(@PathVariable String name,
                                 @Valid @RequestBody ProductUpdateRequest productUpdateRequest) {
        return productService.updateByName(name,productUpdateRequest);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/Delete/{name}")
    ProductResponse deleteByName(@PathVariable String name) {
        return productService.deleteByName(name);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping("/UploadNewProduct")
    ProductResponse uploadNewProduct(@Valid @RequestBody ProductUploadRequest productUploadRequest) {
        return productService.uploadNewProduce(productUploadRequest);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/search")
    List<ProductResponse> searchByName(@RequestParam String name) {
        return productService.searchByName(name);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/search/{name}")
    ProductResponse findByName(@PathVariable String name) {
        return productService.findByName(name);
    }

    @GetMapping
    Page<ProductResponse> findAll(@RequestParam(required = false, defaultValue = "1") int pageNo,
                                  @RequestParam(required = false,defaultValue = "25") int pageSize){
        return productService.findAll(pageNo,pageSize);
    }

}
