package com.example.jdbctemplate.controller;

import com.example.jdbctemplate.entity.Student;
import com.example.jdbctemplate.service.impl.StudentServiceJpaImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student/jpa")
public class StudentJpaController {

    private final StudentServiceJpaImpl studentService;

    @GetMapping
    public ResponseEntity<List<Student>> findAllStudent() {
        return ResponseEntity.ok(studentService.findAllStudents());
    }

    @PatchMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.update(student));
    }
}
