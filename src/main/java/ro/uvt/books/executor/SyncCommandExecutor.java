package ro.uvt.books.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.uvt.books.commands.Command;

@Component
public class SyncCommandExecutor implements CommandExecutor {
    private static final Logger LOGGER = LoggerFactory.getLogger(SyncCommandExecutor.class);

    @Override
    public <T> T execute(Command<T> command) {
        LOGGER.debug("Synchronously executing command {}", command.name());
        return command.execute();
    }
}
