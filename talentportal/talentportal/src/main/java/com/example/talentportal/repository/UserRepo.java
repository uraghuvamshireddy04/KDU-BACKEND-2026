package com.example.talentportal.repository;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import org.springframework.stereotype.Repository;

import com.example.talentportal.model.User;

@Repository
public class UserRepo {

    private final Map<String, User> users = new HashMap<>();
    private final PasswordEncoder passwordEncoder;

    public UserRepo(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;

        register("alice", "pass123", "alice@corp.com", List.of("ROLE_BASIC"));
        register("admin", "admin123", "admin@corp.com", List.of("ROLE_ADMIN"));
    }

    public User register(String userName, String rawPassword, String email, List<String> roles) {
        String encoded = passwordEncoder.encode(rawPassword); 
        System.out.println(encoded);
        User user = new User();
        user.setUserName(userName);
        user.setPassword(encoded);
        user.setEmail(email);
        user.setRoles(roles);
        users.put(userName, user);
        return user;
    }

    public Optional<User> findByUsername(String userName) {
        return Optional.ofNullable(users.get(userName));
    }

    public List<User> findAll() {
        return new ArrayList<>(users.values());
        }
}

