package com.example.talentportal.controller;

import com.example.talentportal.model.User;
import com.example.talentportal.repository.UserRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.talentportal.repository.UserRepo;

import org.springframework.web.bind.annotation.RestController;

import com.example.talentportal.repository.UserRepo;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('BASIC','ADMIN')")
    public List<User> listUsers() {
        return userRepo.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public User addUser(@RequestBody User user) {
        return userRepo.register(user.getUserName(), user.getPassword(), user.getEmail(), user.getRoles());
    }
}

