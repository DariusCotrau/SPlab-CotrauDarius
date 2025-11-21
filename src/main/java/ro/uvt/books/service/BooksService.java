package ro.uvt.books.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.uvt.books.model.Book;
import ro.uvt.books.model.BookRequest;
import ro.uvt.books.persistence.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService {
    private final BookRepository bookRepository;

    public BooksService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        seedData();
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(long id) {
        return bookRepository.findById(id);
    }

    @Transactional
    public Book create(BookRequest request) {
        Book book = new Book(request.getTitle(), request.getAuthor(), request.getIsbn());
        return bookRepository.save(book);
    }

    @Transactional
    public Optional<Book> replace(long id, BookRequest request) {
        return bookRepository.findById(id).map(existing -> {
            existing.setTitle(request.getTitle());
            existing.setAuthor(request.getAuthor());
            existing.setIsbn(request.getIsbn());
            return bookRepository.save(existing);
        });
    }

    @Transactional
    public boolean delete(long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private void seedData() {
        if (bookRepository.count() == 0) {
            bookRepository.save(new Book("Clean Code", "Robert C. Martin", "9780132350884"));
            bookRepository.save(new Book("Design Patterns", "Erich Gamma", "9780201633610"));
        }
    }
}
