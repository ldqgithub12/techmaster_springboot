package com.example.shoppingcartbackend.service;

import com.example.shoppingcartbackend.db.CartDB;
import com.example.shoppingcartbackend.db.CourseDB;
import com.example.shoppingcartbackend.dto.CartItemDto;
import com.example.shoppingcartbackend.exception.NotFoundException;
import com.example.shoppingcartbackend.model.CartItem;
import com.example.shoppingcartbackend.model.Course;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    public List<CartItem> list = CartDB.cart;
    public List<Course> courseList = CourseDB.courses;
    public List<CartItemDto> getAll(){
        List<CartItemDto> cartItemDtos = list.stream().map(cartItem -> {
            CartItemDto cartItemDto =  new CartItemDto();
            cartItemDto.setId(cartItem.getId());
            cartItemDto.setCourse(courseList.get(cartItem.getCourseId()));
            cartItemDto.setCount(cartItem.getCount());
            return cartItemDto;
        }).collect(Collectors.toList());
        return cartItemDtos;
    }
    public void deleteCartItem(int id){
        CartItem cartItem = list.stream().filter(cartItem1 -> cartItem1.getId() == id).findFirst().orElse(null);
        if (cartItem != null){
            list.remove(cartItem);
        }
        else {
            throw new NotFoundException("Không tồn tại id "+ id);
        }
    }
    public void increseItems(int id){
        CartItem cartItem = list.stream().filter(cartItem1 -> cartItem1.getId() == id).findFirst().orElse(null);
        if (cartItem != null){
            cartItem.setCount(cartItem.getCount()+1);
        }
        else {
            throw new NotFoundException("Không tồn tại id "+ id);
        }
    }
    public void decreseItems(int id){
        CartItem cartItem = list.stream().filter(cartItem1 -> cartItem1.getId() == id).findFirst().orElse(null);
        if (cartItem != null){
            cartItem.setCount(cartItem.getCount()-1);
        }
        else {
            throw new NotFoundException("Không tồn tại id "+ id);
        }
    }
}
