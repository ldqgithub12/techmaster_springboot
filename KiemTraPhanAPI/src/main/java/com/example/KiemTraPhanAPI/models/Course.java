package com.example.KiemTraPhanAPI.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private int id;
    @NotEmpty(message = "Tên không được để trống")
    private String name;
    @NotEmpty(message = "Mô tả không được để trống")
    private String description;
    private String type;
    private String[] topics;
    private String thumbnail;
    private User user;
}
