package abudu.product.mappers;

import abudu.product.dto.CartDTO;
import abudu.product.models.Cart;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {
    public CartDTO mapToDTO(Cart cart){
        if(cart == null){
            return null;
        }
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setTotalPrice(cart.getTotalPrice());
        cartDTO.setUserId(cart.getUser());
        cartDTO.setProducts(cart.getProducts().stream().map(new ProductMapper()::mapToDTO).toList());
        cartDTO.setTotalPrice(cart.getTotalPrice());

        return cartDTO;
    }

    public Cart mapToEntity(CartDTO cartDTO){
        if(cartDTO == null){
            return null;
        }
        Cart cart = new Cart();
        cart.setId(cartDTO.getId());
        cart.setTotalPrice(cartDTO.getTotalPrice());
        cart.setUser(cartDTO.getUserId());
        cart.setProducts(cartDTO.getProducts().stream().map(new ProductMapper()::mapToEntity).toList());
        return cart;
    }
}
