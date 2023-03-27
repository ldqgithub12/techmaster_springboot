package com.example.demo_jpa_relationship;

import com.example.demo_jpa_relationship.many_to_many.ClassRepository;
import com.example.demo_jpa_relationship.many_to_many.Classroom;
import com.example.demo_jpa_relationship.many_to_many.Student;
import com.example.demo_jpa_relationship.many_to_many.StudentRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class ManyToManyTests {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassRepository classRepository;

    @Test
    void save_student() {
        Faker faker = new Faker();
        // Tạo 1 số student
        for (int i = 0; i < 30; i++) {
            Student student = Student.builder()
                    .name(faker.name().fullName())
                    .build();
            studentRepository.save(student);
        }
    }

    @Test
    void save_class() {
        Faker faker = new Faker();
        Random rd = new Random();
        List<Student> students = studentRepository.findAll();
        for (int i = 0; i < 5; i++) {
            // Random 1 số học sinh
            List<Student> rdList = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                int rdIndex = rd.nextInt(students.size()); // Random index
                Student std = students.get(rdIndex); // Lấy ra student dựa trên index
                if (!rdList.contains(std)) { // Nếu chưa tồn tại trong ds random thì bổ sung vào, có rồi thì thôi
                    rdList.add(std);
                }
            }

            // Tạo class
            Classroom classroom = Classroom.builder()
                    .name(faker.name().fullName())
                    .students(rdList)
                    .build();
            classRepository.save(classroom);
        }
    }
}
