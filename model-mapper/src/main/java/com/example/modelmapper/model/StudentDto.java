package com.example.modelmapper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto {

    private Integer id;

    private String code;

    private String name;

    private Date birthday;

    private Integer addressId;
}
