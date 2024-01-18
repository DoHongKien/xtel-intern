package com.example.custom.validator.service.impl;

import com.example.custom.validator.entity.Employee;
import com.example.custom.validator.exception.ExistsEmployeeCodeException;
import com.example.custom.validator.repository.EmployeeRepository;
import com.example.custom.validator.service.EmployeeService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final int EMPLOYEE_BY_SIZE = 10;

    private final EmployeeRepository employeeRepository;

    @Override
    public Page<Employee> findAllEmployees(int page) {
        Pageable pageable = PageRequest.of(page, EMPLOYEE_BY_SIZE);
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Employee findEmployeeById(Long employeeId) {
        Specification<Employee> specification = Specification.where(
                (root, query, builder) -> builder.equal(root.get("id"), employeeId)
        );
        return employeeRepository.findOne(specification).orElseThrow();
    }

    @Override
    public Employee findEmployeeByCode(String employeeCode) {
        Specification<Employee> specification = Specification.where(
                (root, query, builder) -> builder.equal(root.get("code"), employeeCode)
        );
        return employeeRepository.findOne(specification).orElseThrow();
    }

    @Override
    public Employee saveEmployee(Employee employee) throws ExistsEmployeeCodeException {
        Specification<Employee> specification = Specification.where((root, query, builder) -> builder.equal(root.get("code"), employee.getCode()));
        boolean isExistsEmployeeCode = employeeRepository.findOne(specification).isPresent();
        if (isExistsEmployeeCode) {
            throw new ExistsEmployeeCodeException("The employee has already been created with employee code " + employee.getCode());
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee, Long employeeId) throws ExistsEmployeeCodeException {
        Specification<Employee> specification = checkUpdateExistsEmployeeCode(employee, employeeId);
        boolean isExistsEmployeeCode = employeeRepository.findOne(specification).isPresent();
        if (isExistsEmployeeCode) {
            throw new ExistsEmployeeCodeException("The employee has already been created with employee code " + employee.getCode());
        }
        Employee saveEmployee = Employee.builder()
                .id(employeeId)
                .code(employee.getCode())
                .name(employee.getName())
                .age(employee.getAge())
                .address(employee.getAddress())
                .build();
        return employeeRepository.save(saveEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    private Specification<Employee> checkUpdateExistsEmployeeCode(Employee employee, Long employeeId) {
        Specification<Employee> findCode = Specification
                .where((root, query, builder) -> builder.equal(root.get("code"), employee.getCode()));
        Specification<Employee> findId = Specification
                .where(((root, query, builder) -> builder.notEqual(root.get("id"), employeeId)));
        return Specification.allOf(findCode, findId);
    }
}
