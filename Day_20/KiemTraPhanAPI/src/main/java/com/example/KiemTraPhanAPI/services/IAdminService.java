package com.example.KiemTraPhanAPI.services;

import com.example.KiemTraPhanAPI.models.AdminResponse;
import com.example.KiemTraPhanAPI.models.Course;

import java.util.List;

public interface IAdminService {
//    public AdminResponse getByAdmin(int page, int pageSize);
    public List<Course> getByAdmin();
    public Course findById(int id);
    public Course addOrUpdateCourse(Course course);
    public Course UpdateCourse(int id, Course updateCourse);
    public void deleteCourse(int id);
}
