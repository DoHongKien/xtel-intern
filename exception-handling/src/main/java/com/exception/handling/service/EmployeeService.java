package com.exception.handling.service;

import com.exception.handling.dto.EmployeeDto;
import com.exception.handling.entity.Employee;
import com.exception.handling.exception.ConflictException;
import com.exception.handling.exception.IdNotFoundException;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAllEmployees();

    Employee findEmployeeById(Long employeeId) throws IdNotFoundException;

    Employee saveEmployee(EmployeeDto employee) throws ConflictException;

    Employee updateEmployee(EmployeeDto employee, Long id) throws IdNotFoundException;

    void deleteEmployeeById(Long employeeId) throws IdNotFoundException;
}
