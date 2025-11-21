package ro.uvt.books.commands;

import ro.uvt.books.service.BooksService;

public class DeleteBookCommand implements Command<Boolean> {
    private final BooksService booksService;
    private final long id;

    public DeleteBookCommand(BooksService booksService, long id) {
        this.booksService = booksService;
        this.id = id;
    }

    @Override
    public Boolean execute() {
        return booksService.delete(id);
    }
}
