package com.example.jdbctemplate.service.impl;

import com.example.jdbctemplate.entity.Student;
import com.example.jdbctemplate.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StudentJdbcTemplateServiceImpl implements StudentService {

    private final JdbcTemplate jdbcTemplate;

    RowMapper<Student> mapper = (rs, rowMapper) -> new Student(
            rs.getLong("id"),
            rs.getString("code"),
            rs.getString("first_name"),
            rs.getString("middle_name"),
            rs.getString("last_name"),
            rs.getDate("dob"),
            rs.getString("address")
    );

    @Override
    public List<Student> findAllStudents() {
        String sql = "SELECT id, code, first_name, middle_name, last_name, dob, address FROM student";
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public Student getStudent(Long studentId) {
        String sql = "SELECT id, code, first_name, middle_name, last_name, dob, address FROM student WHERE id = ?";
        Student student = jdbcTemplate.queryForObject(sql, mapper, studentId);

        if(student != null) {
            return jdbcTemplate.queryForObject(sql, mapper, studentId);
        }
        return null;
    }

    @Override
    public String saveStudent(Student student) {
        String sql = "INSERT INTO student(code, first_name, middle_name, last_name, dob, address) VALUES (?,?,?,?,?,?)";
        int result = jdbcTemplate.update(sql,
                student.getCode(), student.getFirstName(),
                student.getMiddleName(), student.getLastName(),
                student.getDob(), student.getAddress());
        if (result == 1) {
            return "Inserted successfully with student code is " + student.getCode();
        } else {
            return "Failed to insert student";
        }
    }

    @Override
    public String updateStudent(Student student) {
        String sql = "UPDATE student SET code = ?, first_name = ?, middle_name = ?, last_name = ?, dob = ?, address = ? WHERE id = ?";
        int result = jdbcTemplate.update(sql, student.getCode(), student.getFirstName(),
                student.getMiddleName(), student.getLastName(),
                student.getDob(), student.getAddress(), student.getId());
        if (result == 1) {
            return "Updated successfully with student code is " + student.getCode();
        } else {
            return "Failed to update student";
        }
    }

    @Override
    public String deleteStudent(Long studentId) {
        String sql = "DELETE FROM student WHERE id = " + studentId;
        int result = jdbcTemplate.update(sql);
        return result == 1 ? "Deleted student successfully!":"Delete student failed!";
    }
}
