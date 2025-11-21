package ro.uvt.books.commands;

import ro.uvt.books.model.Book;
import ro.uvt.books.service.BooksService;

import java.util.Optional;

public class GetBookByIdCommand implements Command<Optional<Book>> {
    private final BooksService booksService;
    private final long id;

    public GetBookByIdCommand(BooksService booksService, long id) {
        this.booksService = booksService;
        this.id = id;
    }

    @Override
    public Optional<Book> execute() {
        return booksService.findById(id);
    }
}
