package abudu.product.repositories;

import abudu.product.models.Category;
import abudu.product.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, Long> {
    Product findByName(String name);
    Product findByCategoryId(Category categoryId);
    Product findByStock(int stock);
}
