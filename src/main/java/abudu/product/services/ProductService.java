package abudu.product.services;


import abudu.product.dto.ProductDTO;
import abudu.product.exceptions.ResourceNotFoundException;
import abudu.product.mappers.ProductMapper;
import abudu.product.models.Product;
import abudu.product.repositories.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    //Create a new ProductService
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productMapper.mapToEntity(productDTO);
       Product savedProduct = productRepository.save(product);
       return productMapper.mapToDTO(savedProduct);
    }

    //Get all products w sorting
    public List<ProductDTO> getAllProducts(String sortBy) {
        List<Product> products = productRepository.findAll(Sort.by(Sort.Direction.ASC, sortBy));
        return products.stream().map(productMapper::mapToDTO).collect(Collectors.toList());
    }

    //Get a product by id
    public ProductDTO getProductById(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found" + id));
        return productMapper.mapToDTO(product);
    }

    //Update a product
    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found" + id));
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        Product savedProduct = productRepository.save(product);
        return productMapper.mapToDTO(savedProduct);
        }

    //Delete a product
    public void deleteProduct(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found" + id));
        productRepository.delete(product);
    }

}
