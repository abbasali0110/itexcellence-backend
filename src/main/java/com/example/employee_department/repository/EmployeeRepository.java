package com.example.employee_department.repository;




import com.example.employee_department.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
   Optional<Employee> findById(String employeeId); // To find employees by department
}
