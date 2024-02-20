package com.example.executor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String gender;

    private String address;

    private Integer age;

    private LocalDateTime birthday;
    public Student(String name, String gender, String address, Integer age, LocalDateTime birthday) {
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.age = age;
        this.birthday = birthday;
    }
}
