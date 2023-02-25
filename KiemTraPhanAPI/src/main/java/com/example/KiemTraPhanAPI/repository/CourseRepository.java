package com.example.KiemTraPhanAPI.repository;

import com.example.KiemTraPhanAPI.db.CourseDb;
import com.example.KiemTraPhanAPI.models.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepository {
    public List<Course> getAll(){
        return CourseDb.list;
    }
}
