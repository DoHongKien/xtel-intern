package com.exception.handling;

import static org.assertj.core.api.Assertions.assertThat;
import com.exception.handling.entity.Employee;
import com.exception.handling.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void findAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        employees.forEach(System.out::println);
    }

    @Test
    public void findEmployeesById() {
        Optional<Employee> employee = employeeRepository.findById(1L);
        if(employee.isPresent()) {
            System.out.println("Employee: " + employee.get());
            assertThat(employee.get().getId()).isGreaterThan(0);
        }

    }

    @Test
    public void checkExistsEmployeeCode() {
        // Hiện đã tồn tại nhân viên có mã EMPLOYEE1
        boolean existsed = employeeRepository.existsEmployeeByCode("EMPLOYEE1");
        assertThat(existsed).isTrue();// Thực hiện thành công
        assertThat(existsed).isFalse();// Thực hiện thất bại
    }

    @Test
    public void saveEmployee() {
        Employee employee = new Employee();
        employee.setCode("EMPLOYEE10");
        employee.setName("Kien");
        employee.setAge(21);
        employee.setAddress("Hung Yen");
        Employee saveEmployee = employeeRepository.save(employee);
        assertThat(saveEmployee.getId()).isGreaterThan(0);
    }

    @Test
    public void deleteEmployee() {
        employeeRepository.deleteById(4L);
    }
}
