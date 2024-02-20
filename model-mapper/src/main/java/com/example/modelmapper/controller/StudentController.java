package com.example.modelmapper.controller;

import com.example.modelmapper.entity.Student;
import com.example.modelmapper.model.ShowStudentDto;
import com.example.modelmapper.model.StudentDto;
import com.example.modelmapper.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public Page<Student> getAllStudents(@RequestParam Integer page, @RequestParam Integer size) {
        return studentService.getAllStudent(page, size);
    }

    @GetMapping("/get/{id}")
    public ShowStudentDto getStudent(@PathVariable Integer id) {
        return studentService.getStudent(id);
    }


    @PostMapping
    public Student saveStudent(@RequestBody StudentDto studentDto) {
        return studentService.saveStudent(studentDto);
    }
}
