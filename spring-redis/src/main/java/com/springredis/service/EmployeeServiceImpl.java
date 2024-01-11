package com.springredis.service;

import com.springredis.entity.Employee;
import com.springredis.exception.IdNotFoundException;
import com.springredis.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeRedisService employeeRedisService;

    private static final int PAGE_SIZE = 10;

    @Override
    public List<Employee> findAllEmployees(int pageNumber) {

        List<Employee> employees = employeeRedisService.getAllEmployee(pageNumber, PAGE_SIZE);

        // Check if list employee is null then save list from database to redis
        if (employees == null) {
            Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE);
            List<Employee> content = employeeRepository.findAll(pageable).getContent();
            employeeRedisService.saveAllEmployee(content, pageNumber, PAGE_SIZE);
            return content;
        }
        return employees;
    }

    @Override
    public Employee findEmployeeById(Long employeeId) throws IdNotFoundException {
        Employee employee = employeeRedisService.getEmployee(employeeId);

        if (employee == null) {
            Employee employeeInDatabase = employeeRepository
                    .findById(employeeId)
                    .orElseThrow(() -> new IdNotFoundException("Employee has id " + employeeId + " not found"));
            employeeRedisService.saveEmployee(employeeInDatabase);
            return employeeInDatabase;
        }
        return employee;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public ResponseEntity<String> deleteEmployee(Long employeeId) throws IdNotFoundException {
        Employee employeeInDatabase = employeeRepository
                .findById(employeeId)
                .orElseThrow(() -> new IdNotFoundException("Employee has id " + employeeId + " not found"));
        if (employeeInDatabase != null) {
            // Delete the employee from redis before delete the employee from the database
            String key = employeeRedisService.getKey(employeeInDatabase.getId());
            employeeRedisService.clearByKey(key);
            employeeRepository.deleteById(employeeId);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted employee with id " + employeeId);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find employee with id " + employeeId);
        }
    }

}
