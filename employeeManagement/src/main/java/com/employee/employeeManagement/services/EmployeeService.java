package com.employee.employeeManagement.services;

import com.employee.employeeManagement.exceptions.ResourceNotFoundException;
import com.employee.employeeManagement.models.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getAllEmployees();
    Employee findById(long id) throws ResourceNotFoundException;
    Employee save(Employee employee);
    void deleteById(long id);
}
