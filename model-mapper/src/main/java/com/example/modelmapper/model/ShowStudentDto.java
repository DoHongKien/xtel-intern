package com.example.modelmapper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShowStudentDto {

    private String code;

    private String name;

    private Date birthday;

    private String country;

    private String province;

    private String district;

    private String ward;
}
