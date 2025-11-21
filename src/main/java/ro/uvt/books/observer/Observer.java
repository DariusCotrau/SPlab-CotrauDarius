package ro.uvt.books.observer;

import ro.uvt.books.model.Book;

public interface Observer {
    void update(Book book);
}
