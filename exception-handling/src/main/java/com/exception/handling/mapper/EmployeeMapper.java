package com.exception.handling.mapper;

import com.exception.handling.dto.EmployeeDto;
import com.exception.handling.entity.Employee;

public class EmployeeMapper {

    public static Employee convertToEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setCode(employeeDto.getCode());
        employee.setName(employeeDto.getName());
        employee.setAge(employeeDto.getAge());
        employee.setAddress(employeeDto.getAddress());
        return employee;
    }
}
