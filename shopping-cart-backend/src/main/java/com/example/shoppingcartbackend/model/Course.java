package com.example.shoppingcartbackend.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Course {
    private Integer id;
    private String name;
    private String description;
    private String type;
    private List<Category> categories;
    private String thumbnail;
    private Integer price;
    private Double rating;
    private Integer userId;
}
