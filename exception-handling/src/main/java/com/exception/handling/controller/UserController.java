package com.exception.handling.controller;

import com.exception.handling.dto.RequestLogin;
import com.exception.handling.dto.UserDto;
import com.exception.handling.exception.ConflictException;
import com.exception.handling.exception.IdNotFoundException;
import com.exception.handling.exception.UsernameNotFoundException;
import com.exception.handling.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RequestLogin login) throws UsernameNotFoundException {
        return ResponseEntity.ok(userService.login(login));
    }

    @GetMapping("/list")
    public ResponseEntity<?> findAllUser() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto user) throws ConflictException, IdNotFoundException {
        return ResponseEntity.ok(userService.saveUser(user));
    }
}
