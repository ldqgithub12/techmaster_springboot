package com.example.KiemTraPhanAPI.services;

import com.example.KiemTraPhanAPI.exceptions.NotFoundException;
import com.example.KiemTraPhanAPI.models.Category;
import com.example.KiemTraPhanAPI.models.Course;
import com.example.KiemTraPhanAPI.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServicesImpl implements ICourseServices{
    private final CourseRepository courseRepository;

    public CourseServicesImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAll(String type, String name , String categories) {
        List<Course> lists= courseRepository.findCourseDemo(type, name, categories);
        return lists;
//        if (type != null){
//            return lists.stream().filter(item->item.getType().equalsIgnoreCase(type)).collect(Collectors.toList());
//        } else if (name != null) {
//            return lists.stream().filter(item -> item.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
//        }
//        else {
//            return lists.stream().filter(item -> item.getCategory().contains(categories)).collect(Collectors.toList());
//        }
    }

    @Override
    public Course findByID(int id) {
        Course course = courseRepository.findById(id).get();
        if (course != null){
            return course;
        }
        else {
            throw new NotFoundException("Khong tim thay id: "+id);
        }
    }
}
