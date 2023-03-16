package com.example.shoppingcartbackend.controller;

import com.example.shoppingcartbackend.dto.CartItemDto;
import com.example.shoppingcartbackend.model.CartItem;
import com.example.shoppingcartbackend.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @GetMapping("/cartItems")
    public List<CartItemDto> getAll(){
        return cartService.getAll();
    }
    @DeleteMapping("/cartItems/{id}")
    public void deleteItem(@PathVariable int id){
        cartService.deleteCartItem(id);
    }
    @PutMapping("/cartItems/{id}/increment")
    public void increaseItem(@PathVariable int id){
        cartService.increseItems(id);
    }
    @PutMapping("/cartItems/{id}/decrement")
    public void decreaseItem(@PathVariable int id){
        cartService.decreseItems(id);
    }
}
