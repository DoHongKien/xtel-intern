package com.example.springbootswagger.repository;

import com.example.springbootswagger.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
