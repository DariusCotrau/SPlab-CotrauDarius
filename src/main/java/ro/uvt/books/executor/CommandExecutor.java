package ro.uvt.books.executor;

import ro.uvt.books.commands.Command;

public interface CommandExecutor {
    <T> T execute(Command<T> command);
}
