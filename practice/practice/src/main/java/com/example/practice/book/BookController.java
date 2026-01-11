package com.example.practice.book;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('LIBRARIAN','MEMBER')")
    public List<Book> list() {
        return service.getAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('LIBRARIAN')")
    @Operation(summary = "Starts the async book processing")
    public ResponseEntity<Book> create(@Valid @RequestBody BookCreateRequest req) {
        Book created = service.addBookAsync(req.getTitle());

        return ResponseEntity.accepted()
                .location(URI.create("/books")) 
                .body(created);
    }
}


