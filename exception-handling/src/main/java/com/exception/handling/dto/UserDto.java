package com.exception.handling.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private String username;
    private String password;
    private String name;
    private Date dob;
    private Integer age;
}
