package com.example.springbootswagger.controller;

import com.example.springbootswagger.entity.Student;
import com.example.springbootswagger.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<?> findAllStudent() {
        return ResponseEntity.ok(studentService.getListStudent());
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<?> findStudentById(@PathVariable("studentId") Long studentId) {
        return ResponseEntity.ok(studentService.findById(studentId));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.saveStudent(student));
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<?> deleteStudentById(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok("Deleted student successfully!!!");
    }
}
