package com.example.demo_VietAPI.controller;

import com.example.demo_VietAPI.models.Products;
import com.example.demo_VietAPI.services.ProductServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductServices productServices;

    public ProductController(ProductServices productServices) {
        this.productServices = productServices;
    }

    @GetMapping("/")
    public List<Products> getAll(){
        return productServices.getAllProduct();
    }
}
