package com.example.practice.book;

import java.util.UUID;

public class Book {
    private String id;
    private String title;
    private BookStatus status;

    public Book() {}

    public Book(String title, BookStatus status) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.status = status;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public BookStatus getStatus() { return status; }

    public void setId(String id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setStatus(BookStatus status) { this.status = status; }
}

