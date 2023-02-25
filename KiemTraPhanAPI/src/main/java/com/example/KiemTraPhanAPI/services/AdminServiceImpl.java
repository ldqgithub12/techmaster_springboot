package com.example.KiemTraPhanAPI.services;

import com.example.KiemTraPhanAPI.models.AdminResponse;
import com.example.KiemTraPhanAPI.models.Course;
import com.example.KiemTraPhanAPI.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.xml.crypto.Data;
import java.util.List;

@Service
public class AdminServiceImpl implements IAdminService{
    private final CourseRepository courseRepository;

    public AdminServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public AdminResponse getByAdmin(int page, int pageSize) {
        AdminResponse response = new AdminResponse();
        List<Course> list = courseRepository.getAll();
        Course[] data = new Course[pageSize];
        response.setCurPage(page);
        response.setPageSize(pageSize);
        if (list.size()<pageSize){
            response.setTotalPage(1);
        }
        else response.setTotalPage((int)Math.floor(list.size()/pageSize));
        response.setTotalItem(list.size());
        int i = 0;
        if (i >= (page - 1) * pageSize && i < page * pageSize) {
            int j = list.size();
            while (j > 0){
                data[i] = list.get(i);
                i++;
                j--;
            }
        }
        response.setData(data);
        return response;
    }

    @Override
    public Course findById(int id) {
        List<Course> list = courseRepository.getAll();
        return list.stream().filter(item->item.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Course addOrUpdateCourse(Course course) {
        List<Course> list = courseRepository.getAll();
        int id = course.getId();
        Course courses = list.stream().filter(item->item.getId() == id).findFirst().orElse(null);
        if (courses == null){
            list.add(course);
        }
        else {
            courses.setName(course.getName());
            courses.setDescription(courses.getDescription());
            courses.setType(courses.getType());
            courses.setTopics(courses.getTopics());
            courses.setThumbnail(courses.getThumbnail());
            courses.setUser(course.getUser());
        }
        return courses;
    }
    @Override
    public void deleteCourse(int id) {
        List<Course> list = courseRepository.getAll();
        Course course = list.stream().filter(item->item.getId() == id).findFirst().orElse(null);
        list.remove(course);
    }
}
