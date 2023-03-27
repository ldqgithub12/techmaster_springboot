package com.example.demojpa.repository;

import com.example.demojpa.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query(
            value = "select * from student s where upper(s.name) like upper(concat('%', ?1, '%'))",
            countQuery = "select count(s.id) from student s where upper(s.name) like upper(concat('%', ?1, '%'))",
            nativeQuery = true
    )
    Page<Student> findByNameContainsIgnoreCase_NativeQuery(String name, Pageable pageable);
    @Modifying
    @Query("update student s set s.name = ?1 where s.id = ?2")
    void updateName(String name, Integer id);

    // Xóa user theo email
    @Modifying
    @Query("delete from Student s where s.email = ?1")
    void deleteByUserEmail(String email);
}