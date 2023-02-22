package com.example.demo_VietAPI.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Products {
    private Integer id;
    private String name;
    private String brand;
    private Integer count;
}
