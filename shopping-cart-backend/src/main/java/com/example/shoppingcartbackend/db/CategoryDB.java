package com.example.shoppingcartbackend.db;

import com.example.shoppingcartbackend.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryDB {
    public static List<Category> categories = new ArrayList<>(List.of(
            new Category(1, "frontend"),
            new Category(2, "backend"),
            new Category(3, "database"),
            new Category(4, "devops"),
            new Category(5, "AWS"),
            new Category(6, "basic"),
            new Category(7, "mobile")
    ));
}
