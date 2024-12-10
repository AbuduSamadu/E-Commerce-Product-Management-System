package abudu.product.models;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "cart", cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "cart_product",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    private double totalPrice;

    // Constructors
    public Cart() {
    }

    public Cart(Long id, User user, List<Product> products, double totalPrice) {
        this.id = id;
        this.user = user;
        this.products = products;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    // toString
    @Override
    public String toString() {
        return "Cart{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", products=" + products +
                ", totalPrice=" + totalPrice +
                '}';
    }

}
