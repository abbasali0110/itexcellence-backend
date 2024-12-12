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

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    // Method to get all employees
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(employee -> {
            return EmployeeDTO.builder()
                    .id(employee.getId())
                    .name(employee.getName())
                    .email(employee.getEmail())
                    .position(employee.getPosition())
                    .salary(employee.getSalary())
                 .build();
            /*EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setName(employee.getName());
            employeeDTO.setEmail(employee.getEmail());
            employeeDTO.setPosition(employee.getPosition());
            employeeDTO.setSalary(employee.getSalary());
            return employeeDTO;*/
        }).collect(Collectors.toList());

    }

    // Method to get all employees in a department
    /*public List<Employee> getEmployeesInDepartment(String departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }*/
    // Get employees by department ID
    public List<Employee> getEmployeesInDepartment(String departmentId) {
        Department department = departmentRepository.findById(departmentId).orElse(null);
        if (department != null) {
            return department.getEmployees();
        }
        return null;
    }

    // Method to add a new employee in a department
  /*  public Employee addEmployeeToDepartment(Employee employee, String departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new RuntimeException("Department not found"));
        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }*/
    // ToDo final Add new employee to a department
    public Employee addEmployeeToDepartment(String departmentId, Employee employee) {
        Department department = departmentRepository.findById(departmentId).orElse(null);

        if (department != null) {
            employee.setDepartment(department);  // Set the Department (Foreign Key)
            return employeeRepository.save(employee);  // Save Employee with Department
        }

        return null;  // Return null if department not found
    }

    // Delete employee from department
    public boolean deleteEmployeeFromDepartment(String departmentId, String employeeId) {
        Department department = departmentRepository.findById(departmentId).orElse(null);
        if (department != null) {
            Optional<Employee> employee = employeeRepository.findById(employeeId);
            if (employee.isPresent() && employee.get().getDepartment().equals(department)) {
                employeeRepository.delete(employee.get());
                return true;
            }
        }
        return false;
    }

}
