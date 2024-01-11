package com.springredis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springredis.entity.Employee;

import java.util.List;

public interface EmployeeRedisService {

    void clearAll();

    void clearByKey(String key);

    String getKey(Long employeeId);

    List<Employee> getAllEmployee(int pageNumber, int pageSize);

    Employee getEmployee(Long employeeId);

    void saveAllEmployee(List<Employee> employees, int pageNumber, int pageSize);

    void saveEmployee(Employee employee);
}
