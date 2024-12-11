package abudu.product.services;

import abudu.product.exceptions.ResourceNotFoundException;
import abudu.product.models.Category;
import abudu.product.models.Product;
import abudu.product.repositories.CategoryRepository;
import abudu.product.utils.BinaryTree;
import abudu.product.utils.BinaryTreeNode;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final BinaryTree categoryTree;
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryTree = new BinaryTree();
        this.categoryRepository = categoryRepository;
    }

    //Create a new category
    public void addCategory(String name) {
        categoryTree.insert(name);
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
    }

    //Add a product to a category
    public void addProductToCategory(String categoryName, Product product) {
        categoryTree.addProductToCategory(categoryName, product);
        categoryRepository.addProductToCategory(categoryName, product);
    }

    // Retrieve all products within a category
    public BinaryTreeNode getCategory(String categoryName) {
        BinaryTreeNode categoryNode = categoryTree.search(categoryName);
        if (categoryNode == null) {
            throw new ResourceNotFoundException("Category not found: " + categoryName);
        }
        return categoryNode;
    }


}
