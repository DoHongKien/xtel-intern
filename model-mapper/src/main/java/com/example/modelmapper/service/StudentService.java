package com.example.modelmapper.service;

import com.example.modelmapper.entity.Student;
import com.example.modelmapper.model.ShowStudentDto;
import com.example.modelmapper.model.StudentDto;
import org.springframework.data.domain.Page;

public interface StudentService {

    Page<Student> getAllStudent(int page, int size);

    ShowStudentDto getStudent(Integer id);

    Student saveStudent(StudentDto studentDto);
}
