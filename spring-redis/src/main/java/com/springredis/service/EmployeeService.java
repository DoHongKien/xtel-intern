package com.springredis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springredis.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAllEmployees(int pageNumber) throws JsonProcessingException;

    Employee findEmployeeById(Long employeeId);

    Employee saveEmployee(Employee employee);
}
