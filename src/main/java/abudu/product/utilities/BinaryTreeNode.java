package abudu.product.utilities;

import abudu.product.models.Product;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeNode {
    private String categoryName;
    private final List<Product> products;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public BinaryTreeNode(String categoryName) {
        this.categoryName = categoryName;
        this.products = new ArrayList<>();
        this.left = null;
        this.right = null;
    }

    // Getters and Setters
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }
}
