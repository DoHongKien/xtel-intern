package com.exception.handling.service.impl;

import com.exception.handling.dto.RequestLogin;
import com.exception.handling.dto.UserDto;
import com.exception.handling.entity.Role;
import com.exception.handling.entity.User;
import com.exception.handling.exception.ConflictException;
import com.exception.handling.exception.IdNotFoundException;
import com.exception.handling.exception.UsernameNotFoundException;
import com.exception.handling.mapper.UserMapper;
import com.exception.handling.repository.RoleRepository;
import com.exception.handling.repository.UserRepository;
import com.exception.handling.security.jwt.JwtService;
import com.exception.handling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String login(RequestLogin account) throws UsernameNotFoundException {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                account.getUsername(),
                                account.getPassword()));
        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(account.getUsername());
        }
        throw new UsernameNotFoundException("User is invalid");
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(UserDto user) throws IdNotFoundException, ConflictException {
        Role role = roleRepository
                .findById(2L)
                .orElseThrow(() -> new IdNotFoundException("Role with id 2 not found"));

        boolean usernameFound = userRepository.existsUserByUsername(user.getUsername());
        if(usernameFound) {
            throw new ConflictException("Username is already in use by another user");
        }

        User saveUser = UserMapper.convertToUser(user);
        saveUser.addRole(role);
        saveUser.setStatus(true);
        saveUser.setPassword(encoderPassword(user.getPassword()));
        return userRepository.save(saveUser);
    }

    private String encoderPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
