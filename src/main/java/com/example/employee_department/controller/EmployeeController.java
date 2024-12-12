package com.example.employee_department.controller;



import com.example.employee_department.Model.Employee;
import com.example.employee_department.dto.EmployeeDTO;
import com.example.employee_department.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    // GET method to get all employees


    // Get all employees
    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // Get all employees in a specific department
    @GetMapping("/department/{departmentId}")
    public List<Employee> getEmployeesInDepartment(@PathVariable String departmentId) {
        return employeeService.getEmployeesInDepartment(departmentId);
    }


    // Add a new employee to a department
    @PostMapping("/add/{departmentId}")
    public Employee addEmployeeToDepartment(
            @PathVariable String departmentId,      // Path variable for departmentId
            @RequestBody Employee employee) {       // Employee object in request body
        return employeeService.addEmployeeToDepartment(departmentId, employee);
    }
    // Delete an employee from a department
    @DeleteMapping("/department/{departmentId}/employee/{employeeId}")
    public boolean deleteEmployeeFromDepartment(@PathVariable String departmentId, @PathVariable String employeeId) {
        return employeeService.deleteEmployeeFromDepartment(departmentId, employeeId);
    }

}
