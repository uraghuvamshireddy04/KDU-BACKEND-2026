package com.example.library.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.library.model.Book;

@Repository
public class LibraryRepository {
    private final HashMap<Long, Book> bookStore = new HashMap<>();
    private Long counter = 1L;

    public Book addBook(Book book){
        book.setId(counter);
        bookStore.put(counter, book);
        counter++;
        return book;

    }

    public Book findBookById(Long id){
        if(bookStore.containsKey(id)){
            return bookStore.get(id);
        }
        return null;
    }

    public List<Book> listAllBooks(){
        return new ArrayList<>(bookStore.values());
    }

    public Book updateBook(Long id, Book book){
        if(bookStore.containsKey(id)){
            book.setId(id);
            bookStore.put(id,book);
            return book;
        }
        return null;
    }

    public boolean deleteBookById(Long id){
        if(bookStore.containsKey(id)){
            bookStore.remove(id);
            return true;
        }
        return false;
    }

}
