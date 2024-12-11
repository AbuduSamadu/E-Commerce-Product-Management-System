// OrderController.java
package abudu.product.controllers;

import abudu.product.models.Order;
import abudu.product.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place/{cartId}")
    public ResponseEntity<Order> placeOrder(@PathVariable Long cartId, @RequestBody String shippingAddress, @RequestParam Long userId) {
        Order order = orderService.placeOrder(cartId, shippingAddress, userId);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}