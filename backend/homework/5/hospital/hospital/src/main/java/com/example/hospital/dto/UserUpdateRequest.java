package com.example.hospital.dto;

public record UserUpdateRequest(
    String username,
    String timeZone,
    Boolean loggedIn
) {}
