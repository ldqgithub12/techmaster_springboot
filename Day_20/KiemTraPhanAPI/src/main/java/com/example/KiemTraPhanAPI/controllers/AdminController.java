package com.example.KiemTraPhanAPI.controllers;

import com.example.KiemTraPhanAPI.models.AdminResponse;
import com.example.KiemTraPhanAPI.models.Category;
import com.example.KiemTraPhanAPI.models.Course;
import com.example.KiemTraPhanAPI.models.User;
import com.example.KiemTraPhanAPI.services.AdminServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/courses")
public class AdminController {
    private final AdminServiceImpl adminService;

    public AdminController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }
//    @GetMapping("/")
//    public AdminResponse getByAdmin(@RequestParam(required = false,defaultValue = "1") int page, @RequestParam(required = false,defaultValue = "10") int pageSize){
//        return adminService.getByAdmin(page,pageSize);
//    }
    @GetMapping("/")
    public List<Course> getByAdmin (){
        return adminService.getByAdmin();
    }
    @PostMapping("/")
    public Course addOrUpdate(@RequestBody @Valid Course course){
        return adminService.addOrUpdateCourse(course);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable int id, @RequestBody Course course){
        return adminService.UpdateCourse(id,course);
    }
    @GetMapping("/{id}")
    public Course findById(@PathVariable int id){
        return adminService.findById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable int id){
        adminService.deleteCourse(id);
    }
    @GetMapping("/users")
    public List<User> getAllUser(){
        return adminService.getAllUser();
    }
    @GetMapping("/categories")
    public List<Category> getAllCategory(){
        return adminService.getAllCategory();
    }
}
