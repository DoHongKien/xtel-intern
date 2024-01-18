package com.example.custom.validator.controller;

import com.example.custom.validator.entity.Employee;
import com.example.custom.validator.exception.ExistsEmployeeCodeException;
import com.example.custom.validator.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/list")
    public ResponseEntity<Page<Employee>> findAllEmployees(@RequestParam int page) {
        return ResponseEntity.ok(employeeService.findAllEmployees(page));
    }

    @GetMapping("/get-id")
    public ResponseEntity<Employee> findEmployeeById(@RequestParam Long employeeId) {
        return ResponseEntity.ok(employeeService.findEmployeeById(employeeId));
    }

    @GetMapping("/get-code")
    public ResponseEntity<Employee> findEmployeeByCode(@RequestParam String employeeCode) {
        return ResponseEntity.ok(employeeService.findEmployeeByCode(employeeCode));
    }

    @PostMapping("/save")
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) throws ExistsEmployeeCodeException {
        return ResponseEntity.ok(employeeService.saveEmployee(employee));
    }

    @PostMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestParam Long employeeId,
                                                   @Valid @RequestBody Employee employee) throws ExistsEmployeeCodeException {
        return ResponseEntity.ok(employeeService.updateEmployee(employee, employeeId));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteEmployeeById(@RequestParam Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("OK");
    }
}
