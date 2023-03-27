package com.example.KiemTraPhanAPI.services;

import com.example.KiemTraPhanAPI.exceptions.CustomExceptionHandler;
import com.example.KiemTraPhanAPI.exceptions.NotFoundException;
import com.example.KiemTraPhanAPI.models.AdminResponse;
import com.example.KiemTraPhanAPI.models.Course;
import com.example.KiemTraPhanAPI.repository.CourseRepository;
import com.example.KiemTraPhanAPI.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements IAdminService{
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public AdminServiceImpl(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AdminResponse getByAdmin(int page, int pageSize) {
        AdminResponse response = new AdminResponse();
        List<Course> list = courseRepository.findAll();
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
        Course course = courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Không tìm thấy khóa học có id "+id));
        return course;
    }

    @Override
    public Course addOrUpdateCourse(Course course) {
        Course courses = courseRepository.findById(course.getId()).orElse(null);
        if (courses == null){
            courseRepository.save(course);
        }
        else {
            courses.setName(course.getName());
            courses.setDescription(course.getDescription());
            courses.setType(course.getType());
            courses.setThumbnail(course.getThumbnail());
            courses.setPrice(course.getPrice());
            courses.setRating(course.getRating());
            courses.setCategory(course.getCategory());
            courses.setUser(course.getUser());
            courseRepository.save(courses);
        }
        return courses;
    }
    @Override
    public void deleteCourse(int id) {
        Course course = courseRepository.findById(id).orElseThrow(()->new NotFoundException("Không tìm thấy id "+id));
        courseRepository.delete(course);
    }
}
