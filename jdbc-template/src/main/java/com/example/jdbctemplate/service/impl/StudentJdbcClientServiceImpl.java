package com.example.jdbctemplate.service.impl;

import com.example.jdbctemplate.entity.Student;
import com.example.jdbctemplate.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StudentJdbcClientServiceImpl implements StudentService {

    private final JdbcClient jdbcClient;

    @Override
    public List<Student> findAllStudents() {
        String sql = "SELECT * FROM student";
        return jdbcClient
                .sql(sql)
                .query(Student.class)
                .list();
    }

    @Override
    public Student getStudent(Long studentId) {
        String sql = "SELECT * FROM student WHERE id = " + studentId;
        return jdbcClient
                .sql(sql)
                .param("id", studentId)
                .query(Student.class)
                .optional().orElseThrow();
    }

    @Override
    public String saveStudent(Student student) {
        String sql = "INSERT INTO student(code, first_name, middle_name, last_name, dob, address) VALUES (?,?,?,?,?,?)";
        int result = jdbcClient
                .sql(sql)
                .params(List.of(student.getCode(), student.getFirstName(), student.getMiddleName(),
                        student.getLastName(), student.getDob(), student.getAddress()))
                .update();
        return result == 1 ? "Inserted student successfully!" : "Insert student failed!";
    }

    @Override
    public String updateStudent(Student student) {
        String sql = "UPDATE student SET code = ?, first_name = ?, middle_name = ?, last_name = ?, dob = ?, address = ? WHERE id = ?";
        int result = jdbcClient
                .sql(sql)
                .params(List.of(student.getCode(), student.getFirstName(), student.getMiddleName(),
                        student.getLastName(), student.getDob(), student.getAddress(), student.getId()))
                .update();
        return result == 1 ? "Updated student successfully!":"Update student failed!";
    }

    @Override
    public String deleteStudent(Long studentId) {
        String sql = "DELETE FROM student WHERE id = :id";
        int result = jdbcClient
                .sql(sql)
                .param("id", studentId)
                .update();
        return result == 1 ? "Deleted student successfully!":"Delete student failed!";
    }
}
