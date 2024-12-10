package abudu.product.dto;

import abudu.product.models.User;

import java.util.List;

public class CartDTO {
    private Long id;
    private double totalPrice;
    private User userId;
    private List<ProductDTO> products;
    private String createdAt;
    private String updatedAt;

    public CartDTO() {
    }

    public CartDTO(Long id, double totalPrice, User userId, List<ProductDTO> products, String createdAt, String updatedAt) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.products = products;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUserId() {
        return userId;
    }
    public void setUserId(User userId) {
        this.userId = userId;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }
    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    // toString

    @Override
    public String toString() {
        return "Cart{" +
                "id='" + id + '\'' +
                ", totalPrice=" + totalPrice +
                ", userId='" + userId + '\'' +
                ", products=" + products +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }

}
