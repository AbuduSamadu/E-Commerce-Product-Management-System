package abudu.product.utils;


import abudu.product.exceptions.ResourceNotFoundException;
import abudu.product.models.Product;

public class BinaryTree {
    private BinaryTreeNode root;

    public BinaryTree() {
        this.root = null;
    }

    // Insert a category
    public void insert(String categoryName) {
        root = insertRec(root, categoryName);
    }

    private BinaryTreeNode insertRec(BinaryTreeNode node, String categoryName) {
        if (node == null) {
            return new BinaryTreeNode(categoryName);
        }

        if (categoryName.compareTo(node.getCategoryName()) < 0) {
            node.setLeft(insertRec(node.getLeft(), categoryName));
        } else if (categoryName.compareTo(node.getCategoryName()) > 0) {
            node.setRight(insertRec(node.getRight(), categoryName));
        }

        return node;
    }

    // Find a category
    public BinaryTreeNode search(String categoryName) {
        return searchRec(root, categoryName);
    }

    private BinaryTreeNode searchRec(BinaryTreeNode node, String categoryName) {
        if (node == null || node.getCategoryName().equals(categoryName)) {
            return node;
        }

        if (categoryName.compareTo(node.getCategoryName()) < 0) {
            return searchRec(node.getLeft(), categoryName);
        }

        return searchRec(node.getRight(), categoryName);
    }

    // Add a product to a specific category
    public void addProductToCategory(String categoryName, Product product) {
        BinaryTreeNode categoryNode = search(categoryName);
        if (categoryNode != null) {
            categoryNode.addProduct(product);
        } else {
            throw new ResourceNotFoundException("Category not found: " + categoryName);
        }
    }
}
