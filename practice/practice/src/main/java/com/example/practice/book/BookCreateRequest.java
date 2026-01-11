package com.example.practice.book;

import jakarta.validation.constraints.NotBlank;

public class BookCreateRequest {
    @NotBlank
    private String title;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}

