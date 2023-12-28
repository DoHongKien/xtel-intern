package com.example.springbatchschedule.repository;

import com.example.springbatchschedule.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
