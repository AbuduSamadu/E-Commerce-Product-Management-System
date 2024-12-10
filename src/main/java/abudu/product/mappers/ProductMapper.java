package abudu.product.mappers;


import abudu.product.dto.ProductDTO;
import abudu.product.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO  mapToDTO(Product product){
        if(product == null){
            return null;
        }
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setImgUrl(product.getImgUrl());
        productDTO.setCategoryId(product.getCategory());
        productDTO.setStock(product.getStock());
        return productDTO;
    }

    public Product mapToEntity(ProductDTO productDTO){
        if(productDTO == null){
            return null;
        }
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(productDTO.getImgUrl());
        product.setStock(productDTO.getStock());
        return product;
    }
}
