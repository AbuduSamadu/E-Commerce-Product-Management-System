package abudu.product.dto;

import java.util.List;

public class CartDTO {
    private String id;
    private double totalPrice;
    private String userId;
    private List<ProductDTO> products;
    private String createdAt;
    private String updatedAt;

    public CartDTO() {
    }

    public CartDTO(String id, double totalPrice, String userId, List<ProductDTO> products, String createdAt, String updatedAt) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.products = products;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
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
