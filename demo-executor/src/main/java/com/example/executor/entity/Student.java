package com.example.executor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public Student(String name, String gender, String address, Integer age) {
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.age = age;
    }
}
