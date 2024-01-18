package com.example.custom.validator.service;

import com.example.custom.validator.entity.Employee;
import com.example.custom.validator.exception.ExistsEmployeeCodeException;
import org.springframework.data.domain.Page;

public interface EmployeeService {

    Page<Employee> findAllEmployees(int page);

    Employee findEmployeeById(Long employeeId);

    Employee findEmployeeByCode(String employeeCode);

    Employee saveEmployee(Employee employee) throws ExistsEmployeeCodeException;

    Employee updateEmployee(Employee employee, Long employeeId) throws ExistsEmployeeCodeException;

    void deleteEmployee(Long employeeId);
}
