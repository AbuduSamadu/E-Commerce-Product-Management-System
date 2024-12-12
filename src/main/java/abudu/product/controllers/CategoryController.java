package abudu.product.controllers;

import abudu.product.models.Category;
import abudu.product.models.Product;
import abudu.product.services.CategoryService;
import abudu.product.utilities.BinaryTreeNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Endpoint to add a new category
    @PostMapping("/add")
    public ResponseEntity<String> addCategory(@RequestBody Category category) {
        categoryService.addCategory(category.getName());
        return ResponseEntity.ok("Category added successfully: " + category.getName());
    }

    // Endpoint to add a product to a category
    @PostMapping("/{categoryName}/products")
    public ResponseEntity<String> addProductToCategory(
            @PathVariable String categoryName,
            @RequestBody Product product) {
        categoryService.addProductToCategory(categoryName, product);
        return ResponseEntity.ok("Product added successfully to category: " + categoryName);
    }

    // Endpoint to retrieve all products within a category
    @GetMapping("/{categoryName}/products")
    public ResponseEntity<BinaryTreeNode> getCategoryProducts(@PathVariable String categoryName) {
        BinaryTreeNode categoryNode = categoryService.getCategory(categoryName);
        return ResponseEntity.ok(categoryNode);
    }
}