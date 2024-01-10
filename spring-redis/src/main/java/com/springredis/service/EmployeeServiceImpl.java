package com.springredis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springredis.entity.Employee;
import com.springredis.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    private final EmployeeRedisService employeeRedisService;

    private static final int PAGE_SIZE = 10;

    @Override
    public List<Employee> findAllEmployees(int pageNumber) throws JsonProcessingException {

        List<Employee> employees = employeeRedisService.getAllEmployee(pageNumber, PAGE_SIZE);

        // Check if list employee is null then save list from database to redis
        if(employees == null) {
            Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE);
            List<Employee> content = employeeRepository.findAll(pageable).getContent();
            return employeeRedisService.saveAllEmployee(content, pageNumber, PAGE_SIZE);
        }
        return employees;
    }

    @Cacheable(key = "#employeeId", value = "employee")
    @Override
    public Employee findEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}
