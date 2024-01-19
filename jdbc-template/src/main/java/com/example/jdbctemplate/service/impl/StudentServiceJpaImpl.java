package com.example.jdbctemplate.service.impl;

import com.example.jdbctemplate.entity.Student;
import com.example.jdbctemplate.repository.StudentRepository;
import com.example.jdbctemplate.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceJpaImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<Student> findAllStudents() {
        return null;
    }

    @Override
    public Student getStudent(Long studentId) {
        return null;
    }

    @Override
    public String saveStudent(Student student) {
        return null;
    }

    @Override
    public String updateStudent(Student student) {
        return null;
    }

    @Override
    public void deleteStudent(Long studentId) {

    }
}
