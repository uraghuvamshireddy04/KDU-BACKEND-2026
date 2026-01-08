package com.example.library.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.library.model.Book;
import com.example.library.repository.LibraryRepository;

@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;

    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public Book addBook(Book book){
        return libraryRepository.addBook(book);
    }

    public Book findBookById(Long id){
        return libraryRepository.findBookById(id);
    }

    public Map<String, Object> listAllBooks(int page,
            int size,
            String author,
            String sortBy,
            String order){
        List<Book> filteredBooks = libraryRepository.listAllBooks();

        //filter books by author
        if(author != null){
            filteredBooks = filteredBooks.stream()
                .filter(books -> books.getAuthor().equalsIgnoreCase(author))
                .toList();
        }

        //sort books by title
        Comparator<Book> comparator = Comparator.comparing(Book::getTitle);
        if("desc".equalsIgnoreCase(order)){
            comparator = comparator.reversed();
        }

        filteredBooks.sort(comparator);

        //pagination
        int start = page * size;
        int end = Math.min(start + size, filteredBooks.size());

        List<Book> pageData = start >= filteredBooks.size()
            ? List.of()
            : filteredBooks.subList(start, end);

        return Map.of(
            "page", page,
            "size", size,
            "totalItems", filteredBooks.size(),
            "data", pageData
        );
    }

    public Book updateBook(Long id, Book book){
        return libraryRepository.updateBook(id, book);
    }

    public boolean deleteBookById(Long id){
        return libraryRepository.deleteBookById(id);
    }
}
