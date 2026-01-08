package com.example.library.controller;

import java.util.Map;

import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.model.Book;
import com.example.library.service.LibraryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/books")
public class LibraryController {
    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService){
        this.libraryService = libraryService;
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book){
        return new ResponseEntity<>(libraryService.addBook(book), HttpStatus.CREATED); 
    }

    @GetMapping("/{id}")
    public EntityModel<Book> getBook(@PathVariable Long id) {
        Book book = libraryService.findBookById(id);

        return EntityModel.of(
            book,
            linkTo(methodOn(LibraryController.class).getBook(id)).withSelfRel(),
            linkTo(methodOn(LibraryController.class).listAllBooks(0,10,null,"title","asc"))
                    .withRel("all-books")
    );
}


    @GetMapping
    public ResponseEntity<Map<String, Object>> listAllBooks(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(required = false) String author,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String order

    ){
        return new ResponseEntity<>(libraryService.listAllBooks(page, size, author, sortBy, order), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@Valid @PathVariable Long id, @RequestBody Book book){
        Book newBook = libraryService.updateBook(id, book);
        if(newBook == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        boolean isDeleted = libraryService.deleteBookById(id);
        if(isDeleted){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);    }
}
