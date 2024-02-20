package com.example.jdbctemplate.controller;

import com.example.jdbctemplate.dto.StudentDto;
import com.example.jdbctemplate.entity.Student;
import com.example.jdbctemplate.service.impl.StudentServiceJpaImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student/jpa")
public class StudentJpaController {

    private final StudentServiceJpaImpl studentService;

    @GetMapping
    public ResponseEntity<List<StudentDto>> findAllStudent() {
        List<StudentDto> students = studentService.findAllStudents()
                .stream()
                .map(this::mapStudentDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(students);
    }

    @PatchMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.update(student));
    }

    private StudentDto mapStudentDto(Student student) {
        return StudentDto.builder()
                .code(student.getCode())
                .fullName(student.getFullName())
                .dob(student.getDob())
                .address(student.getAddress())
                .build();
    }
}
