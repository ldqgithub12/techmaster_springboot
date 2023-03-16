package com.example.shoppingcartbackend.db;

import com.example.shoppingcartbackend.model.CartItem;

import java.util.ArrayList;
import java.util.List;

public class CartDB {
    public static List<CartItem> cart = new ArrayList<>(List.of(
            new CartItem(1,1,2),
            new CartItem(2,2,2)
    ));

}
