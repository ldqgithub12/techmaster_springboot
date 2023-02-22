package com.example.demo_VietAPI.repository;

import com.example.demo_VietAPI.DB.ProductDB;
import com.example.demo_VietAPI.models.Products;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductRepository {
    public List<Products> getAll(){
        return ProductDB.list;
    }
}
