package com.example.springbatchschedule.service.impl;

import com.example.springbatchschedule.entity.Student;
import com.example.springbatchschedule.repository.StudentRepository;
import com.example.springbatchschedule.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<Student> getListStudent() {
        return studentRepository.findAll();
    }
}
