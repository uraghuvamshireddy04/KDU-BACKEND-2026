package com.example.practice.book;

import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final BookService bookService;

    public AnalyticsController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/audit")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public Map<String, Long> audit() {
        return bookService.auditByStatus();
    }
}

