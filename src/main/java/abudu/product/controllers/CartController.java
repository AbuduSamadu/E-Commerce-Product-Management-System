package abudu.product.controllers;

import abudu.product.models.Cart;
import abudu.product.models.Product;
import abudu.product.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{cartId}/add")
    public ResponseEntity<Cart> addToCart(@PathVariable Long cartId, @RequestParam String productId, @RequestParam int quantity) {
        Cart updatedCart = cartService.addToCart(cartId, productId, quantity);
        return ResponseEntity.ok(updatedCart);
    }

    @PostMapping("/{cartId}/remove")
    public ResponseEntity<Cart> removeFromCart(@PathVariable Long cartId, @RequestParam Long productId, @RequestParam int quantity) {
        Cart updatedCart = cartService.removeFromCart(cartId, productId, quantity);
        return ResponseEntity.ok(updatedCart);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long cartId) {
        Cart cart = cartService.getCart(cartId);
        return ResponseEntity.ok(cart);
    }
}