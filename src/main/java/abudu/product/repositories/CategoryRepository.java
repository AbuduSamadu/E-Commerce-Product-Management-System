package abudu.product.repositories;

import abudu.product.models.Category;
import abudu.product.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Modifying
    @Transactional
    @Query("    UPDATE Category c " +
            "SET c.products = :product " +
            "WHERE c.name = :categoryName")
    void addProductToCategory(@Param("categoryName") String categoryName, @Param("product") Product product);
}
