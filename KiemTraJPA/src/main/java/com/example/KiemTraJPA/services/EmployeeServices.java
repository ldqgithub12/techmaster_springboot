package com.example.KiemTraJPA.services;

import com.example.KiemTraJPA.models.Employee;
import com.example.KiemTraJPA.repository.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServices {
    @Autowired
    EmployeeRepository employeeRepository;
    EntityManagerFactory emf;
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public List<Employee> findAllCustom(String lastName){
        Query namedQuery = getEntityManager().createNamedQuery("Employee.findAllCustom");
        namedQuery.setParameter("lastName", lastName);
        return (List<Employee>) namedQuery.getResultList();
    }

}
