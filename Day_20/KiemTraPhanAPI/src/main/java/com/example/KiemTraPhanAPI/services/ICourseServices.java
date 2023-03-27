package com.example.KiemTraPhanAPI.services;

import com.example.KiemTraPhanAPI.models.Category;
import com.example.KiemTraPhanAPI.models.Course;

import java.util.List;

public interface ICourseServices {
    public List<Course> getAll(String type, String name, String categories);
    public Course findByID(int id);
}
