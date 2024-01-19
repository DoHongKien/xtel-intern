package com.example.jdbctemplate.repository;

import com.example.jdbctemplate.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
