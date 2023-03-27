package com.example.KiemTraPhanAPI.services;

import com.example.KiemTraPhanAPI.models.AdminResponse;
import com.example.KiemTraPhanAPI.models.Course;

public interface IAdminService {
    public AdminResponse getByAdmin(int page, int pageSize);
    public Course findById(int id);
    public Course addOrUpdateCourse(Course course);
    public void deleteCourse(int id);
}
