package com.example.employee_department.controller;



import com.example.employee_department.Model.Department;
import com.example.employee_department.dto.DepartmentDTO;
import com.example.employee_department.dto.EmployeeDTO;
import com.example.employee_department.service.DepartmentService;
import com.example.employee_department.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    // Get all departments with employees (avoiding circular dependency using DTOs)
    @GetMapping
    public List<DepartmentDTO> getAllDepartments() {
        return departmentService.getAllDepartments();
    }
    // Save  departments
    @PostMapping()
    public List<Department> saveAllDepartments(@RequestBody List<Department> departments) {
        return departmentService.saveAllDepartments(departments);
    }

    @DeleteMapping("/{departmentId}/employees/{employeeId}")
    public String deleteEmployeeFromDepartment(
            @PathVariable String departmentId,
            @PathVariable String employeeId) {
        return departmentService.deleteEmployeeFromDepartment(departmentId, employeeId);
    }


}

