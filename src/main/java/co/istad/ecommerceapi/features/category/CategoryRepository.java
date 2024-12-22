package co.istad.ecommerceapi.features.category;

import co.istad.ecommerceapi.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findById(Integer id);

   Boolean existsByCategoryName(String categoryName);
    Optional<Category> findByCategoryName(String categoryName);

    // select All
//    @Query("SELECT c from Category c")
//    List<Category> findAll();
}
