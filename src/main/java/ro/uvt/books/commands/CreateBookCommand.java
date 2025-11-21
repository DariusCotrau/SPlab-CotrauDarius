package ro.uvt.books.commands;

import ro.uvt.books.model.Book;
import ro.uvt.books.model.BookRequest;
import ro.uvt.books.service.BooksService;

public class CreateBookCommand implements Command<Book> {
    private final BooksService booksService;
    private final BookRequest request;

    public CreateBookCommand(BooksService booksService, BookRequest request) {
        this.booksService = booksService;
        this.request = request;
    }

    @Override
    public Book execute() {
        simulateExternalIsbnValidation();
        return booksService.create(request);
    }

    private void simulateExternalIsbnValidation() {
        // Simulate a slow downstream dependency that motivates async handling.
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
