package com.example.KiemTraJPA.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@NamedQuery(name = "Employee.findAllCustom", query = "SELECT e FROM Employee e")
public class Employee {
    @Id
    private int id;
    private String emailAddress;
    private String firstName;
    private String lastName;
}
