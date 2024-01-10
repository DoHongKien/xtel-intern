package com.exception.handling.controller;

import com.exception.handling.dto.EmployeeDto;
import com.exception.handling.entity.Employee;
import com.exception.handling.exception.ConflictException;
import com.exception.handling.exception.IdNotFoundException;
import com.exception.handling.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> findAllEmployees() {
        return ResponseEntity.ok(employeeService.findAllEmployees());
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable Long employeeId) throws IdNotFoundException {
        return ResponseEntity.ok(employeeService.findEmployeeById(employeeId));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDto employee) throws ConflictException {
        return ResponseEntity.ok(employeeService.saveEmployee(employee));
    }

    @PutMapping("/update/{employeeId}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeeDto employeeDto) throws IdNotFoundException {
        return ResponseEntity.ok(employeeService.updateEmployee(employeeDto, employeeId));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Long employeeId) throws IdNotFoundException {
        employeeService.deleteEmployeeById(employeeId);
        return ResponseEntity.ok("OK");
    }
}
