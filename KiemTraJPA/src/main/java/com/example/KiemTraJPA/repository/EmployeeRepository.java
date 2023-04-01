package com.example.KiemTraJPA.repository;

import com.example.KiemTraJPA.models.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
     List<Employee> findByEmailAddressAndLastName(String email, String lastName);
     List<Employee> findAllByFirstNameOrLastName(String firstName, String lastName);
     List<Employee> findByLastNameOrderByFistNameAsc(String lastName);
     List<Employee> findByFirstNameIgnoreCase(String firstName);

     List<Employee> findAllCustom();

     //Phần này là câu 10
     Page<Employee> findByLastName(String lastName, PageRequest pageRequest);
     Iterable<Employee> findAllDemoSort(Sort sort);
}
