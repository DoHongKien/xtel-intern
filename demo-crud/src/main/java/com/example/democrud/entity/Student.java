package com.example.democrud.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Student's name should'nt be bull")
    private String name;

    @NotNull(message = "Student's gender should'nt be bull")
    private String gender;

    @NotNull(message = "Student's address should'nt be null")
    private String address;

    @Min(value = 1, message = "Student's age must be greater than or equal to 1")
    @Max(value = 150, message = "Student's age must be less than or equal to 150")
    private Integer age;
}
