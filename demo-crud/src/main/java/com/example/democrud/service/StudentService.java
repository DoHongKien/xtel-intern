package com.example.democrud.service;

import com.example.democrud.entity.Student;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface StudentService {

    List<Student> findAllStudent();

    Student findStudentById(Integer id);

    void exportExcel() throws IOException;

    ResponseEntity<?> saveStudent(Student student);

    void deleteStudent(Integer id);
}
