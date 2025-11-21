package ro.uvt.books.commands;

import ro.uvt.books.model.Book;
import ro.uvt.books.model.BookRequest;
import ro.uvt.books.service.BooksService;

public class UpdateBookCommand implements Command<Book> {
    private final BooksService booksService;
    private final long id;
    private final BookRequest request;

    public UpdateBookCommand(BooksService booksService, long id, BookRequest request) {
        this.booksService = booksService;
        this.id = id;
        this.request = request;
    }

    @Override
    public Book execute() {
        return booksService.replace(id, request);
    }
}
