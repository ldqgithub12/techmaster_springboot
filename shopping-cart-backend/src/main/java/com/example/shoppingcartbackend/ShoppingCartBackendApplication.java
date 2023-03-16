package com.example.shoppingcartbackend;

import com.example.shoppingcartbackend.db.CategoryDB;
import com.example.shoppingcartbackend.model.Category;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ShoppingCartBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartBackendApplication.class, args);
    }

}
