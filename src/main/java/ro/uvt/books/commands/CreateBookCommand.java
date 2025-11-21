package ro.uvt.books.commands;

import ro.uvt.books.model.Book;
import ro.uvt.books.model.BookRequest;
import ro.uvt.books.observer.AllBooksSubject;
import ro.uvt.books.service.BooksService;

public class CreateBookCommand implements Command<Book> {
    private final BooksService booksService;
    private final BookRequest request;
    private final AllBooksSubject allBooksSubject;

    public CreateBookCommand(BooksService booksService, BookRequest request, AllBooksSubject allBooksSubject) {
        this.booksService = booksService;
        this.request = request;
        this.allBooksSubject = allBooksSubject;
    }

    @Override
    public Book execute() {
        simulateExternalIsbnValidation();
        Book book = booksService.create(request);
        if (allBooksSubject != null) {
            allBooksSubject.add(book);
        }
        return book;
    }

    private void simulateExternalIsbnValidation() {
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
