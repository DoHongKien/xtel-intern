package com.example.jdbctemplate.controller;

import com.example.jdbctemplate.entity.Student;
import com.example.jdbctemplate.service.impl.StudentJdbcTemplateServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student/jdbc-template")
public class StudentJdbcTemplateController {

    private final StudentJdbcTemplateServiceImpl studentService;

    @GetMapping
    public ResponseEntity<List<Student>> findAllStudents() {
        return ResponseEntity.ok(studentService.findAllStudents());
    }

    @GetMapping("/get-student")
    public ResponseEntity<Student> findStudentById(@RequestParam Long studentId) {
        return ResponseEntity.ok(studentService.getStudent(studentId));
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.saveStudent(student));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateStudent(student));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStudent(@RequestParam Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok("OK");
    }
}
