package abudu.product.services;

import abudu.product.exceptions.ResourceNotFoundException;
import abudu.product.models.Cart;
import abudu.product.models.CartItem;
import abudu.product.models.Order;
import abudu.product.models.OrderItem;
import abudu.product.repositories.CartRepository;
import abudu.product.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public OrderService(OrderRepository orderRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    @Transactional
    public Order placeOrder(Long cartId, String shippingAddress, Long userId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found: " + cartId));

        if(cart.getCartItems().isEmpty()){
            throw new ResourceNotFoundException("Cart is empty");
        }

        Order order = new Order();
        order.setId(userId);
        order.setShippingAddress(shippingAddress);
        order.setProducts(new ArrayList<>());


        //Convert cart items to order items
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItems.add(orderItem);
        }


        order.setProducts(cart.getCartItems().stream().map(CartItem::getProduct).toList());
        order.setTotalAmount(calculateTotalAmount(orderItems));

        // Save the order
        Order savedOrder = orderRepository.save(order);

        // Clear the cart
        cart.getCartItems().clear();
        cartRepository.save(cart);

        return savedOrder;
    }

    private double calculateTotalAmount(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(orderItem -> orderItem.getProduct().getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())))
                .mapToDouble(BigDecimal::doubleValue)
                .sum();
    }
}
