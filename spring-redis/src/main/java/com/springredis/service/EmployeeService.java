package com.springredis.service;

import com.springredis.entity.Employee;
import com.springredis.exception.IdNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAllEmployees(int pageNumber);

    Employee findEmployeeById(Long employeeId) throws IdNotFoundException;

    Employee saveEmployee(Employee employee);

    ResponseEntity<String> deleteEmployee(Long employeeId) throws IdNotFoundException;
}
