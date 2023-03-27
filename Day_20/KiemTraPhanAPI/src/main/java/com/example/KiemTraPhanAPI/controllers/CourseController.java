package com.example.KiemTraPhanAPI.controllers;

import com.example.KiemTraPhanAPI.models.Category;
import com.example.KiemTraPhanAPI.models.Course;
import com.example.KiemTraPhanAPI.services.CourseServicesImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {
    private final CourseServicesImpl courseServices;

    public CourseController(CourseServicesImpl courseServices) {
        this.courseServices = courseServices;
    }
    @GetMapping("/")
    public List<Course> getAll(@RequestParam(required = false) String type,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String categories){
        return courseServices.getAll(type,name,categories);
    }
    @GetMapping("/{id}")
    public Course findById(@PathVariable int id){
        return courseServices.findByID(id);
    }
}
