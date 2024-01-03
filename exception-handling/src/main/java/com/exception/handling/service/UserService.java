package com.exception.handling.service;

import com.exception.handling.dto.RequestLogin;
import com.exception.handling.dto.UserDto;
import com.exception.handling.entity.User;
import com.exception.handling.exception.ConflictException;
import com.exception.handling.exception.IdNotFoundException;
import com.exception.handling.exception.UsernameNotFoundException;

import java.util.List;

public interface UserService {

    String login(RequestLogin account) throws UsernameNotFoundException;

    List<User> findAllUsers();

    User saveUser(UserDto user) throws IdNotFoundException, ConflictException;
}
