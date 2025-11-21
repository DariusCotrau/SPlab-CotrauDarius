package ro.uvt.books.service;

import org.springframework.stereotype.Service;
import ro.uvt.books.model.Book;
import ro.uvt.books.model.BookRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BooksService {
    private final Map<Long, Book> books = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public BooksService() {
        // Seed with a couple of demo books to keep responses interesting.
        create(new BookRequest("Clean Code", "Robert C. Martin", "9780132350884"));
        create(new BookRequest("Design Patterns", "Erich Gamma", "9780201633610"));
    }

    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    public Optional<Book> findById(long id) {
        return Optional.ofNullable(books.get(id));
    }

    public Book create(BookRequest request) {
        long id = idGenerator.incrementAndGet();
        Book book = new Book(id, request.getTitle(), request.getAuthor(), request.getIsbn());
        books.put(id, book);
        return book;
    }

    public Book replace(long id, BookRequest request) {
        Book book = new Book(id, request.getTitle(), request.getAuthor(), request.getIsbn());
        books.put(id, book);
        return book;
    }

    public boolean delete(long id) {
        return books.remove(id) != null;
    }
}
