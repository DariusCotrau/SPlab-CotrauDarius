package ro.uvt.books.commands;

public interface Command<T> {
    T execute();

    default String name() {
        return getClass().getSimpleName();
    }
}
