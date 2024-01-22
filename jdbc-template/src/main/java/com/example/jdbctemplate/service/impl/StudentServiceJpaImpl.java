package com.example.jdbctemplate.service.impl;

import com.example.jdbctemplate.entity.Student;
import com.example.jdbctemplate.repository.StudentRepository;
import com.example.jdbctemplate.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceJpaImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
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

    public Student update(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public String deleteStudent(Long studentId) {
        return null;
    }
}
