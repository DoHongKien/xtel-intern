package com.exception.handling.mapper;

import com.exception.handling.dto.UserDto;
import com.exception.handling.entity.User;

public class UserMapper {

    public static User convertToUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setDob(userDto.getDob());
        user.setAge(userDto.getAge());
        return user;
    }
}
