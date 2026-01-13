package com.example.hospital.dto;

import jakarta.validation.constraints.NotBlank;

public record ShiftTypeCreateRequest(
    @NotBlank String name,
    String description,
    boolean active
) {}

