package com.example.KiemTraPhanAPI.services;

import com.example.KiemTraPhanAPI.exceptions.NotFoundException;
import com.example.KiemTraPhanAPI.models.Category;
import com.example.KiemTraPhanAPI.models.Course;
import com.example.KiemTraPhanAPI.models.User;
import com.example.KiemTraPhanAPI.repository.CategoryRepository;
import com.example.KiemTraPhanAPI.repository.CourseRepository;
import com.example.KiemTraPhanAPI.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements IAdminService{
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public AdminServiceImpl(CourseRepository courseRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Course> getByAdmin() {
//        AdminResponse response = new AdminResponse();
        List<Course> list = courseRepository.findAll();
//        Course[] data = new Course[pageSize];
//        response.setCurPage(page);
//        response.setPageSize(pageSize);
//        if (list.size()<pageSize){
//            response.setTotalPage(1);
//        }
//        else response.setTotalPage((int)Math.floor(list.size()/pageSize));
//        response.setTotalItem(list.size());
//        int i = 0;
//        if (i >= (page - 1) * pageSize && i < page * pageSize) {
//            int j = list.size();
//            while (j > 0){
//                data[i] = list.get(i);
//                i++;
//                j--;
//            }
//        }
//        response.setData(data);
        return  list;
    }

    @Override
    public Course findById(int id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Không tìm thấy khóa học có id "+id));
        return course;
    }

    @Override
    public Course addOrUpdateCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course UpdateCourse(int id, Course updateCourse) {
        Course course = courseRepository.findById(id).orElseThrow(()->new NotFoundException("Không tìm thấy id "+id));
        course.setName(updateCourse.getName());
        course.setDescription(updateCourse.getDescription());
        course.setType(updateCourse.getType());
        course.setThumbnail(updateCourse.getThumbnail());
        course.setPrice(updateCourse.getPrice());
        course.setRating(updateCourse.getRating());
        courseRepository.save(course);
        return course;
    }

    @Override
    public void deleteCourse(int id) {
        Course course = courseRepository.findById(id).orElseThrow(()->new NotFoundException("Không tìm thấy id "+id));
        courseRepository.delete(course);
    }
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
}
