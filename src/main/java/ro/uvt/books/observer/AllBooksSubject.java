package ro.uvt.books.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.uvt.books.model.Book;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class AllBooksSubject implements Subject {
    private static final Logger LOGGER = LoggerFactory.getLogger(AllBooksSubject.class);
    private final List<Observer> observers = new CopyOnWriteArrayList<>();

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
        LOGGER.debug("Attached observer {}", observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
        LOGGER.debug("Detached observer {}", observer);
    }

    @Override
    public void notifyObservers(Book book) {
        for (Observer observer : observers) {
            try {
                observer.update(book);
            } catch (Exception e) {
                LOGGER.warn("Failed to notify observer {}", observer, e);
            }
        }
    }

    public void add(Book book) {
        notifyObservers(book);
    }
}
