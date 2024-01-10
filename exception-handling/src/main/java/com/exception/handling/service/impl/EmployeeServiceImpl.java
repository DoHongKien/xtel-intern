package com.exception.handling.service.impl;

import com.exception.handling.dto.EmployeeDto;
import com.exception.handling.entity.Employee;
import com.exception.handling.exception.ConflictException;
import com.exception.handling.exception.IdNotFoundException;
import com.exception.handling.mapper.EmployeeMapper;
import com.exception.handling.repository.EmployeeRepository;
import com.exception.handling.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(Long employeeId) throws IdNotFoundException {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IdNotFoundException("Employee has id is " + employeeId + " not found"));
    }

    @Override
    public Employee saveEmployee(EmployeeDto employee) throws ConflictException {
        boolean codeFound = employeeRepository.existsEmployeeByCode(employee.getCode());
        if(codeFound) {
            throw new ConflictException("Employee's code is already in use by another employee");
        }
        Employee saveEmployee = EmployeeMapper.convertToEmployee(employee);
        return employeeRepository.save(saveEmployee);
    }

    @Override
    public Employee updateEmployee(EmployeeDto employee, Long employeeId) throws IdNotFoundException {
        Employee employeeInDatabase = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IdNotFoundException("Employee has id is " + employeeId + " not found"));

        if(employeeInDatabase != null) {
            Employee saveEmployee = EmployeeMapper.convertToEmployee(employee);
            saveEmployee.setId(employeeId);
            saveEmployee.setCode(employeeInDatabase.getCode());
            return employeeRepository.save(saveEmployee);
        }
        return null;
    }

    @Override
    public void deleteEmployeeById(Long employeeId) throws IdNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IdNotFoundException("Employee has id is " + employeeId + " not found"));
        if(employee != null) {
            employeeRepository.deleteById(employeeId);
        }
    }
}
