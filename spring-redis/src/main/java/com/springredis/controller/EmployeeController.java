package com.springredis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springredis.entity.Employee;
import com.springredis.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/lists")
    public ResponseEntity<?> findAllEmployees(@RequestParam(defaultValue = "0") Integer pageNumber) throws JsonProcessingException {
        return ResponseEntity.ok(employeeService.findAllEmployees(pageNumber));
    }

    @GetMapping("/get/{employeeId}")
    public ResponseEntity<?> findById(@PathVariable Long employeeId) {
        return ResponseEntity.ok(employeeService.findEmployeeById(employeeId));
    }

    @PostMapping("save")
    public ResponseEntity<?> saveEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.saveEmployee(employee));
    }
}
