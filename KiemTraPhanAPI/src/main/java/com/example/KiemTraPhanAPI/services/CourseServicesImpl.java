package com.example.KiemTraPhanAPI.services;

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
    public List<Course> getAll(String type, String name, String[] topics) {
        List<Course> lists= courseRepository.getAll();
        if (type != null){
            return lists.stream().filter(item->item.getType().equalsIgnoreCase(type)).collect(Collectors.toList());
        } else if (name != null) {
            return lists.stream().filter(item->item.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
        } else if (topics != null) {
            return lists.stream().filter(item->item.getTopics().equals(topics)).collect(Collectors.toList());
        }
        return lists;
    }

    @Override
    public Course findByID(int id) {
        List<Course> lists= courseRepository.getAll();
        return lists.stream().filter(item->item.getId() == id).findFirst().orElse(null);
    }
}
