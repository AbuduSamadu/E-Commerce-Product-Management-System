package abudu.product.utils;

import abudu.product.models.Product;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class SecurityUtil {

    public static String hashPassword(String password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();

            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    public static byte[] generateSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    public static boolean validatePassword(String inputPassword, String hashedPassword, byte[] salt) {
        return hashedPassword.equals(hashPassword(inputPassword, salt));
    }

    public static class BinaryTree {
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
                throw new IllegalArgumentException("Category not found: " + categoryName);
            }
        }
    }

    public static class BinaryTreeNode {
        private String categoryName;
        private List<Product> products;
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
}