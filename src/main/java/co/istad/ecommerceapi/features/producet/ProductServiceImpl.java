package co.istad.ecommerceapi.features.producet;

import co.istad.ecommerceapi.domain.Category;
import co.istad.ecommerceapi.domain.Product;
import co.istad.ecommerceapi.features.category.CategoryRepository;
import co.istad.ecommerceapi.features.producet.dto.ProductResponse;
import co.istad.ecommerceapi.features.producet.dto.ProductUpdateRequest;
import co.istad.ecommerceapi.features.producet.dto.ProductUploadRequest;
import co.istad.ecommerceapi.mapper.CategoryMapper;
import co.istad.ecommerceapi.mapper.ProductMapper;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;


    @Override
    public ProductResponse updateByName(String name, ProductUpdateRequest productUpdateRequest) {

        Product product = productRepository.findByProductName(name)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found"
                ));

        if (productUpdateRequest.price().compareTo(product.getPrice()) < 0 ) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Price must be greater than or equal to 0"
                );
        }
        productMapper.fromUpdateProductRequestPartially(productUpdateRequest, product);

        Product savedProduct = productRepository.save(product);

        return productMapper.toProduceResponse(savedProduct);
    }

    @Override
    public ProductResponse deleteByName(String name) {

        Product product = productRepository.findByProductName(name)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found"
                ));

        productRepository.delete(product);
        return productMapper.toProduceResponse(product);
    }

    @Override
    public ProductResponse uploadNewProduce(ProductUploadRequest productUploadRequest) {

        if (productRepository.existsByProductName(productUploadRequest.productName())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Name already exists");
        }

        Category category = categoryRepository.findByCategoryName(productUploadRequest.categoryName())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Category not found"
                ));

//        log.info("Category Name: {}", category.getCategoryName());

        Product product = productMapper.fromProductUploadRequest(productUploadRequest);
        product.setAvailable(true);
        product.setCategory(category);

        Product saveProduce = productRepository.save(product);

        return productMapper.toProduceResponse(saveProduce);
    }


    @Override
    public List<ProductResponse> searchByName(String name) {

        List<Product> products = productRepository.searchByProductName(name);
        if (products.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product not found"
            );
        }
        return productMapper.toProductListResponse(products);
    }

    @Override
    public ProductResponse findByName(String name) {

        Product product = productRepository.findByProductName(name)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found"
                ));

        return productMapper.toProduceResponse(product);
    }

    @Override
    public Page<ProductResponse> findAll(int pageNo, int pageSize) {

        if (pageNo < 1) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "The page number must be greater than 0"
            );
        }
        if (pageSize < 1) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "The page size must be greater than 0"
            );
        }

        Sort sortById =Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize, sortById);

        Page<Product> produceByPage = productRepository.findAll(pageRequest);

        return produceByPage.map(productMapper::toProduceResponse);
    }
}
