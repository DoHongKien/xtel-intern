package com.example.springbootswagger.service.impl;

import com.example.springbootswagger.entity.Student;
import com.example.springbootswagger.repository.StudentRepository;
import com.example.springbootswagger.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<Student> getListStudent() {
        log.info("Start getListStudent()");
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Long studentId) {
        log.info("Start findById() with student id {}", studentId);
        return studentRepository.findById(studentId).orElseThrow();
    }

    @Override
    public Student saveStudent(Student student) {
        log.info("Start saveStudent() with student is {}", student);
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long studentId) {
        log.info("Start deleteStudent() with student id is {}", studentId);
        studentRepository.deleteById(studentId);
    }
}
