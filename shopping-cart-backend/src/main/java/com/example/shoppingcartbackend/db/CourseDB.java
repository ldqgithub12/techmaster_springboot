package com.example.shoppingcartbackend.db;

import com.example.shoppingcartbackend.model.Category;
import com.example.shoppingcartbackend.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseDB {
    public static List<Category> list = CategoryDB.categories;
    public static List<Course> courses = new ArrayList<>(List.of(
            new Course(1, "Java", "Khóa học java", "online", List.of(new Category[]{list.get(1),list.get(2)}), "thumbnail", 10_000_000, 3.5,1),
            new Course(2, "Javascript", "Khóa học javascript", "offline",List.of(new Category[]{list.get(1),list.get(2)}), "thumbnail", 20_000_000, 3.5,1),
            new Course(3, "Devops", "Khóa học devops", "online",List.of(new Category[]{list.get(1),list.get(2)}), "thumbnail", 10_000_000, 3.5,1)
    ));

}
