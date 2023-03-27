package com.example.KiemTraPhanAPI.repository;

import com.example.KiemTraPhanAPI.db.CourseDb;
import com.example.KiemTraPhanAPI.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
    @Query(nativeQuery = true, value = "select c.* from course c\n" +
            "left join course_category cc \n" +
            "on c.id = cc.course_id\n" +
            "left join category ct\n" +
            "on cc.category_id = ct.id\n" +
            "where (:name is null or c.name = :name)\n" +
            "and (:type is null or c.type = :type)\n" +
            "and (:category is null or ct.name = :category)\n" +
            "group by c.id")
    List<Course> findCourseDemo(@Param("name") String name, @Param("type") String type, @Param("category") String category);
//    public List<Course> getAll(){
//        return CourseDb.list;
//    }
}
