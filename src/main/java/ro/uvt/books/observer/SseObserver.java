package ro.uvt.books.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import ro.uvt.books.model.Book;

public class SseObserver implements Observer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SseObserver.class);
    private final SseEmitter emitter;
    private final AllBooksSubject subject;

    public SseObserver(SseEmitter emitter, AllBooksSubject subject) {
        this.emitter = emitter;
        this.subject = subject;
    }

    @Override
    public void update(Book book) {
        try {
            emitter.send(book, MediaType.APPLICATION_JSON);
        } catch (Exception e) {
            LOGGER.warn("Failed to send SSE update, detaching observer", e);
            subject.detach(this);
            emitter.completeWithError(e);
        }
    }

    @Override
    public String toString() {
        return "SseObserver@" + Integer.toHexString(hashCode());
    }
}
