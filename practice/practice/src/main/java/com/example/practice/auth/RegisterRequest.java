package com.example.practice.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class RegisterRequest {
    @NotBlank private String username;
    @NotBlank private String password;

    // expects "MEMBER" or "LIBRARIAN"
    @NotEmpty private List<String> roles;

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public List<String> getRoles() { return roles; }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setRoles(List<String> roles) { this.roles = roles; }
}
