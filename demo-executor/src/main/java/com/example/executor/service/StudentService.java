package com.example.executor.service;

import com.example.executor.entity.Student;
import com.example.executor.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Async
    public CompletableFuture<List<Student>> saveStudents(MultipartFile file) throws Exception {
        long start = System.currentTimeMillis();
        List<Student> students = parseCsv(file);
        logger.info("saving list of students of size {} - thread name: {}", students.size(), Thread.currentThread().getName());
        students = studentRepository.saveAll(students);
        long end = System.currentTimeMillis();
        logger.info("Total time {}", end - start);
        return CompletableFuture.completedFuture(students);
    }

    @Async
    public CompletableFuture<List<Student>> findAllStudents() {
        logger.info("get list of user by " + Thread.currentThread().getName());
        List<Student> students = studentRepository.findAll();
        return CompletableFuture.completedFuture(students);
    }

    @Async
    public CompletableFuture<Student> findStudent(Integer studentId) {
        logger.info("find student by id {}", studentId);
        Student student = studentRepository.findById(studentId).orElseThrow();
        return CompletableFuture.completedFuture(student);
    }

    public List<Student> parseCsv(MultipartFile file) throws Exception {
        final List<Student> students = new ArrayList<>();
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    Student student = new Student(
                            data[1],
                            data[2],
                            data[3],
                            Integer.parseInt(data[4]));
                    students.add(student);
                }
                return students;
            }

        } catch (final IOException e) {
            logger.error(e.getMessage());
            throw new Exception("Failed to parse CSV file {}", e);
        }
    }
}
