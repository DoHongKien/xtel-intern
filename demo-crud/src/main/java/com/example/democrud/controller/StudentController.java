package com.example.democrud.controller;

import com.example.democrud.entity.Student;
import com.example.democrud.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get-list")
    public ResponseEntity<?> findAllStudent() {
        return ResponseEntity.ok(studentService.findAllStudent());
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<?> findStudentById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentService.findStudentById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.saveStudent(student));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable("id") Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("OK");
    }
}
