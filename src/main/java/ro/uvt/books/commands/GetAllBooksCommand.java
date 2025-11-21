package ro.uvt.books.commands;

import ro.uvt.books.model.Book;
import ro.uvt.books.service.BooksService;

import java.util.List;

public class GetAllBooksCommand implements Command<List<Book>> {
    private final BooksService booksService;

    public GetAllBooksCommand(BooksService booksService) {
        this.booksService = booksService;
    }

    @Override
    public List<Book> execute() {
        return booksService.findAll();
    }
}
