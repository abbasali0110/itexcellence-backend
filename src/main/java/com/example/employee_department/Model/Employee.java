package com.example.employee_department.Model;



import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    private String id;
    private String name;
    private String email;
    private String position;
    private double salary;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;
    // Constructors, Getters, Setters

}

