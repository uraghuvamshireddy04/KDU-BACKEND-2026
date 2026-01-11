package com.example.practice.book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    private final Map<String, Book> store = new ConcurrentHashMap<>();

    public Book save(Book book) {
        store.put(book.getId(), book);
        return book;
    }

    public Optional<Book> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Book> findAll() {
        return new ArrayList<>(store.values());
    }
}


