package com.example.practice.user;

import java.util.List;

public class AppUser {
    private String username;
    private String passwordHash;
    private List<String> roles; // "ROLE_MEMBER", "ROLE_LIBRARIAN"

    public AppUser() {}

    public AppUser(String username, String passwordHash, List<String> roles) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.roles = roles;
    }

    public String getUsername() { return username; }
    public String getPasswordHash() { return passwordHash; }
    public List<String> getRoles() { return roles; }

    public void setUsername(String username) { this.username = username; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public void setRoles(List<String> roles) { this.roles = roles; }
}
