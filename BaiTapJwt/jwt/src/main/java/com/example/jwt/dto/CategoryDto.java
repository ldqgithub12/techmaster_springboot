package com.example.jwt.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CategoryDto {
    private Integer id;
    private String name;
    private Long used; // Số bài blog được áp dụng category này
}
