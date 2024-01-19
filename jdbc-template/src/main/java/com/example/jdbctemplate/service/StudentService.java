package com.example.jdbctemplate.service;

import com.example.jdbctemplate.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAllStudents();

    Student getStudent(Long studentId);

    String saveStudent(Student student);

    String updateStudent(Student student);

    void deleteStudent(Long studentId);
}
