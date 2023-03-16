package com.example.shoppingcartbackend.dto;

import com.example.shoppingcartbackend.model.Course;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CartItemDto {
    private Integer id;
    private Course course;
    private Integer count;
}
