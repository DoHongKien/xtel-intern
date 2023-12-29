package com.example.springbootswagger.service;

import com.example.springbootswagger.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getListStudent();

    Student findById(Long studentId);

    Student saveStudent(Student student);

    void deleteStudent(Long studentId);
}
