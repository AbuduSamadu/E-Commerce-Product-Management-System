package abudu.product.mappers;

import abudu.product.dto.OrderDTO;
import abudu.product.models.Order;
import abudu.product.models.Product;
import abudu.product.models.enums.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderDTO mapToDTO(Order order) {
        if (order == null) {
            return null;
        }
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setUserId(order.getUser());
        orderDTO.setProductIds(order.getProducts().stream()
                .map(Product::getId)
                .collect(Collectors.toList()).reversed());
        orderDTO.setTotalAmount(order.getTotalAmount());
        orderDTO.setStatus(order.getStatus().name());
        orderDTO.setShippingAddress(order.getShippingAddress());
        orderDTO.setCreatedAt(order.getCreatedAt());
        orderDTO.setUpdatedAt(order.getUpdatedAt());
        return orderDTO;
    }

    public Order mapToEntity(OrderDTO orderDTO) {
        if (orderDTO == null) {
            return null;
        }
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setStatus(OrderStatus.valueOf(orderDTO.getStatus()));
        order.setShippingAddress(orderDTO.getShippingAddress());
        order.setCreatedAt(orderDTO.getCreatedAt());
        order.setUpdatedAt(orderDTO.getUpdatedAt());
        return order;
    }
}