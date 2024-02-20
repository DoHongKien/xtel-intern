package com.example.jdbctemplate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentDto {

    private String code;

    private String fullName;

    private Date dob;

    private String address;
}
