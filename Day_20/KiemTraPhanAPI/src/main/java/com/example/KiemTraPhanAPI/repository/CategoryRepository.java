package com.example.KiemTraPhanAPI.repository;

import com.example.KiemTraPhanAPI.models.Category;
import com.example.KiemTraPhanAPI.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
