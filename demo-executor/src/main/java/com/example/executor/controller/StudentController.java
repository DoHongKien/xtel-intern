package com.example.executor.controller;

import com.example.executor.entity.Student;
import com.example.executor.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/students", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
    public ResponseEntity<?> saveStudents(@RequestParam("files") MultipartFile[] files) throws Exception {
        for(MultipartFile file: files) {
            studentService.saveStudents(file);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/students", produces = "application/json")
    public CompletableFuture<ResponseEntity<List<Student>>> findAllStudents() {
        return studentService.findAllStudents().thenApply(ResponseEntity::ok);
    }

    @GetMapping(value = "/getMultipleStudents", produces = "application/json")
    public ResponseEntity<?> getStudents() {
        CompletableFuture<List<Student>> students1 = studentService.findAllStudents();
        CompletableFuture<List<Student>> students2 = studentService.findAllStudents();
        CompletableFuture<List<Student>> students3 = studentService.findAllStudents();
        CompletableFuture.allOf(students1, students2, students3).join();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value = "/get-student")
    public CompletableFuture<List<Student>> getStudent(@RequestParam("id") List<Integer> ids) {
        List<CompletableFuture<Student>> completableFuture = new ArrayList<>();
        for (Integer id : ids) {
            CompletableFuture<Student> student = studentService.findStudent(id);
            completableFuture.add(student);
        }

        // Xác nhận khi nào hoàn thành lấy dữ liệu
        CompletableFuture<Void> allOf = CompletableFuture.allOf(completableFuture.toArray(new CompletableFuture[0]));

        // Chuyển từ CompletableFuture<Void> -> CompletableFuture<List<Student>> và trả về kết quả
        return allOf.thenApply(v ->
                completableFuture.stream().map(CompletableFuture::join).collect(Collectors.toList())
        );
    }
}
