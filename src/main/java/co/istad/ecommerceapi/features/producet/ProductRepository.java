package co.istad.ecommerceapi.features.producet;

import co.istad.ecommerceapi.domain.Category;
import co.istad.ecommerceapi.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByProductNameAndSize(String productName, String size);

    Boolean existsByStock(Integer stock);

    Boolean existsBySize(String size);

    List<Product> findByCategory(Category category);

    @Query("SELECT p FROM Product p WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :productName, '%'))")
    List<Product> searchByProductName(String productName);

    Optional<Product> findByProductName(String productName);

    Boolean existsByProductName(String productName);


//    Optional<Product>findByName(String name);
}
