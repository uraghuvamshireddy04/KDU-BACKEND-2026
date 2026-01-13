package com.example.hospital.dto;

import java.util.UUID;

public record UserResponse(
    UUID id,
    String username,
    boolean loggedIn,
    String timeZone,
    UUID tenantId
) {}

