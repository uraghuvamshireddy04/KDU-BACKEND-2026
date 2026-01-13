package com.example.hospital.dto;

import jakarta.validation.constraints.NotBlank;

public record UserCreateRequest(
    @NotBlank String username,
    boolean loggedIn,
    @NotBlank String timeZone
) {}
