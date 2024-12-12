package com.example.employee_department.repository;



import com.example.employee_department.Model.Department;
import com.example.employee_department.Model.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    //List<Employee> findByDepartmentId(String departmentId);
    Optional<Department> findById(String departmentId);
   // List<Employee> findByDepartmentId(String departmentId);
}

