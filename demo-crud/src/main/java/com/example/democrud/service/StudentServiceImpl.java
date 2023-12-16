package com.example.democrud.service;

import com.example.democrud.entity.Student;
import com.example.democrud.exception.IdNotFoundException;
import com.example.democrud.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    @Override
    public List<Student> findAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student findStudentById(Integer id) {
        return studentRepository.findById(id).orElseThrow();
    }

    @Override
    public ResponseEntity<?> saveStudent(Student student) {
        if(studentRepository.existsStudentByName(student.getName())) {
            return ResponseEntity.ok("Tên không được trùng");
        }
        return ResponseEntity.ok(studentRepository.save(student));
    }

    @Override
    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }
}
