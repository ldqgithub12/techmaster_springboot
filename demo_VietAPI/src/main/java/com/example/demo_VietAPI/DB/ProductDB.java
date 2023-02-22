package com.example.demo_VietAPI.DB;

import com.example.demo_VietAPI.models.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductDB {
    public static List<Products> list = new ArrayList<>(List.of(
            new Products(1,"IP13 promax","Apple",100),
            new Products(2,"IP14 promax","Apple",10),
            new Products(3,"Samsung S23 Ultra","Samsung",10),
            new Products(4,"Oppo gi gi do","Oppo",100)
    ));
}
