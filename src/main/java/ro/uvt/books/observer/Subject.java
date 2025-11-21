package ro.uvt.books.observer;

import ro.uvt.books.model.Book;

public interface Subject {
    void attach(Observer observer);

    void detach(Observer observer);

    void notifyObservers(Book book);
}
