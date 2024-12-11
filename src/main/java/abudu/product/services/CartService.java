package abudu.product.services;

import abudu.product.exceptions.ResourceNotFoundException;
import abudu.product.models.Cart;
import abudu.product.models.CartItem;
import abudu.product.models.Product;
import abudu.product.repositories.CartRepository;
import abudu.product.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Cart addToCart(Long Id, String productId, int quantity) {
        // Fetch cart or create a new one
        Cart cart = cartRepository.findById(Id).orElseGet(() -> {
            return new Cart(Id);
        });

        // Fetch product and check stock
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + productId));

        if (product.getStock() < quantity) {
            throw new ResourceNotFoundException("Insufficient stock for product: " + product.getName());
        }

        // Deduct stock and save the product
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);

        // Add or update cart item
        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> false)
                .findFirst()
                .orElseGet(() -> new CartItem(product, 0));

        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        cart.getCartItems().add(cartItem);

        // Save the cart
        return cartRepository.save(cart);
    }

    @Transactional
    public Cart removeFromCart(Long Id, Long productId, int quantity) {
        Cart cart = cartRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found: " +Id));

        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Product not in cart: " + productId));

        if (cartItem.getQuantity() < quantity) {
            throw new IllegalArgumentException("Quantity to remove exceeds the quantity in the cart");
        }

        // Update or remove cart item
        cartItem.setQuantity(cartItem.getQuantity() - quantity);
        if (cartItem.getQuantity() == 0) {
            cart.getCartItems().remove(cartItem);
        }

        // Restore stock
        Product product = cartItem.getProduct();
        product.setStock(product.getStock()+ quantity);
        productRepository.save(product);

        // Save the cart
        return cartRepository.save(cart);
    }

    public Cart getCart(Long Id) {
        return cartRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found: " + Id));
    }
}
