package com.example.democrud.repository;

import com.example.democrud.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    boolean existsStudentByName(String name);
}
