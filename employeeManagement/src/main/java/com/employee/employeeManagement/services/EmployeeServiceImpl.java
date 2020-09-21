package com.employee.employeeManagement.services;

import com.employee.employeeManagement.exceptions.ResourceNotFoundException;
import com.employee.employeeManagement.models.Employee;
import com.employee.employeeManagement.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    @Override
    @Transactional
    public Employee findById(long id) throws ResourceNotFoundException {
        return employeeRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Employee not found")
        );
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    @Transactional
    public void deleteById(long id) { employeeRepository.deleteById(id); }
}
