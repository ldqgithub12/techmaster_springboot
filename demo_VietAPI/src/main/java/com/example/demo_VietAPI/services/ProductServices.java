package com.example.demo_VietAPI.services;

import com.example.demo_VietAPI.models.Products;
import com.example.demo_VietAPI.repository.ProductRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices {
    private final ProductRepository productRepository;


    public ProductServices(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Products> getAllProduct(){
        return productRepository.getAll();
    }

    public Integer calculateCart(){
        List<Products> list = productRepository.getAll();
        list.stream().reduce(0,(a,b)->a);
    }
}
