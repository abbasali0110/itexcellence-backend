package com.example.employee_department.service;

import com.example.employee_department.Model.Department;
import com.example.employee_department.Model.Employee;
import com.example.employee_department.dto.DepartmentDTO;
import com.example.employee_department.dto.EmployeeDTO;

import com.example.employee_department.repository.DepartmentRepository;
import com.example.employee_department.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Map;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;


    // Fetch all departments from the database
    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();

        return departments.stream().map(department -> {
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setId(department.getId());
            departmentDTO.setName(department.getName());
            departmentDTO.setLocation(department.getLocation());

            departmentDTO.setEmployees(getEmployeeDTOList(department.getEmployees()));

            return departmentDTO;

        }).collect(Collectors.toList());
    }

     private List<EmployeeDTO> getEmployeeDTOList(List<Employee> employeeList){
       List<EmployeeDTO> list=new ArrayList<>();

       for(Employee employee: employeeList){
           list.add(EmployeeDTO.builder()
                   .id(employee.getId())
                    .name(employee.getName())
                    .email(employee.getEmail())
                    .position(employee.getPosition())
                     .salary(employee.getSalary())
                   .build());
       }
       return list;
     }


    // Add a new department to the database
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }
    public List<Department> saveAllDepartments(List<Department> departments) {
        return departmentRepository.saveAll(departments); // Save all departments
    }


    // Method to delete an employee from a department
    @Transactional
    public String deleteEmployeeFromDepartment(String departmentId, String employeeId) {
        // Check if the department exists
        Department department = departmentRepository.findById(departmentId).orElse(null);
        if (department == null) {
            return "Department not found!";
        }

        // Find the employee by their ID
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null) {
            return "Employee not found!";
        }

        // Check if the employee belongs to the specified department
        if (!employee.getDepartment().getId().equals(departmentId)) {
            return "Employee does not belong to the given department!";
        }


        // Delete the employee from the department
        employeeRepository.delete(employee);
        return "Employee successfully deleted from department!";
    }




    @Transactional
    public void addEmployeeToDepartment(String departmentId, Employee employee) {
        // Fetch department
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        // Associate the employee with the department
        employee.setDepartment(department);

        // Save the employee and update the department
        employeeRepository.save(employee);
        departmentRepository.save(department);
    }

}


