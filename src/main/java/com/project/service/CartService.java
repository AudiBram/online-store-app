package com.project.service;

import com.project.dto.CartDto;
import com.project.dto.CartItemDto;
import com.project.dto.CartListDto;
import com.project.entity.Cart;
import com.project.entity.Customer;
import com.project.entity.Product;
import com.project.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;

    public void addCart(CartDto cartDto) {
        Product product = productService.findById(cartDto.getProductId());

        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setQuantity(cartDto.getQuantity());
        cart.setCreatedAt(new Date());

        cartRepository.save(cart);
    }

    public CartListDto getAll() {
        List<Cart> cartList = cartRepository.findAll();
        List<CartItemDto> cartItems = new ArrayList<>();

        for(Cart cart : cartList) {
            CartItemDto cartItemDto = new CartItemDto(cart);
            cartItems.add(cartItemDto);
        }

        CartListDto cartListDto = new CartListDto();
        cartListDto.setCartItems(cartItems);
        return cartListDto;
    }

    public void deleteCart(Long id) {
        boolean exists = cartRepository.existsById(id);
        if(!exists) {
            throw new IllegalArgumentException("Id not found");
        }
        cartRepository.deleteById(id);
    }
}
