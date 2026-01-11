package com.example.practice.book;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.annotation.PreDestroy;

@Service
public class BookService {

    private static final Logger log = LoggerFactory.getLogger(BookService.class);

    private final BookRepository repo;

    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public Book addBookAsync(String title) {
        Book book = new Book(title, BookStatus.PROCESSING);
        repo.save(book);

        executor.submit(() -> printBarcodeAndMarkAvailable(book.getId()));

        return book;
    }

    private void printBarcodeAndMarkAvailable(String bookId) {
        try {
            log.info("Barcode printing started bookId={}", bookId);
            Thread.sleep(30000); 

            repo.findById(bookId).ifPresent(b -> {
                b.setStatus(BookStatus.AVAILABLE);
                repo.save(b); 
            });

            log.info("Barcode printing finished bookId={} status=AVAILABLE", bookId);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.warn("Barcode printing interrupted bookId={}", bookId);
        } catch (Exception e) {
            log.error("Barcode printing failed bookId={}", bookId, e);
        }
    }

    public List<Book> getAll() {
        return repo.findAll();
    }

    public Map<String, Long> auditByStatus() {
    return repo.findAll().stream()
            .collect(Collectors.groupingBy(
                    b -> b.getStatus().name(),   
                    Collectors.counting()        
            ));
    }

    @PreDestroy
    public void shutdown() {
        executor.shutdown();
    }
}


