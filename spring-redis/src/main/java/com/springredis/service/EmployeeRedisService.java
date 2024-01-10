package com.springredis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springredis.entity.Employee;

import java.util.List;

public interface EmployeeRedisService {

    void clear();

    List<Employee> getAllEmployee(int pageNumber, int pageSize) throws JsonProcessingException;

    List<Employee> saveAllEmployee(List<Employee> employees, int pageNumber, int pageSize) throws JsonProcessingException;
}
