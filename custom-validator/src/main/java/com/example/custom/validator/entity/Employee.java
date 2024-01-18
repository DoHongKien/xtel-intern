package com.example.custom.validator.entity;

import com.example.custom.validator.validator.custom_annotation.PositiveNumber;
import com.example.custom.validator.validator.custom_annotation.StartWithEmployee;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The employee code must be empty")
    @StartWithEmployee
    private String code;

    @Length(min = 1, max = 100, message = "The emplloyee name is required")
    private String name;

    @PositiveNumber
    @Max(value = 150, message = "The employee age must be less than or equal to 150 years old")
    private Integer age;

    @NotBlank(message = "The employee address must be empty")
    private String address;
}
