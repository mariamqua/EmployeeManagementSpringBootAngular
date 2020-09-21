package com.employee.employeeManagement.controllers;

import com.employee.employeeManagement.exceptions.ResourceNotFoundException;
import com.employee.employeeManagement.models.Employee;
import com.employee.employeeManagement.repositories.EmployeeRepository;
import com.employee.employeeManagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired

    EmployeeService employeeService;


    @GetMapping("/employees")
    public List<Employee> getAllEmployees() throws ResourceNotFoundException {
        return employeeService.getAllEmployees();
    }

    //@PostMapping("/employees" )
    @PostMapping(value = "/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee){

        return employeeService.save(employee);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Employee employee= employeeService.findById(employeeId);
        return ResponseEntity.ok().body(employee);

    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> upadateEmployee(@PathVariable(value = "id") Long employeeId,
                                                    @Valid @RequestBody Employee Newemployee)
            throws ResourceNotFoundException {
        Employee employee= employeeService.findById(employeeId);
        employee.setEmail(Newemployee.getEmail());
        employee.setFirstname(Newemployee.getFirstname());
        employee.setLastname(Newemployee.getLastname());

        final Employee upatedEmployee= employeeService.save(employee);

        return ResponseEntity.ok(upatedEmployee);
    }

    @DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId) {
         employeeService.deleteById(employeeId);
         Map<String, Boolean> response=new HashMap<>();
         response.put("deleted", Boolean.TRUE);

        return response;
    }

}
