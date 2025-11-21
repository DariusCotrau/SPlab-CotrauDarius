package ro.uvt.books.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.uvt.books.commands.Command;

import jakarta.annotation.PreDestroy;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class AsyncCommandExecutor {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncCommandExecutor.class);
    private final ExecutorService executor;
    private final ConcurrentMap<UUID, AsyncJob<?>> jobs = new ConcurrentHashMap<>();

    public AsyncCommandExecutor() {
        this.executor = Executors.newFixedThreadPool(Math.max(2, Runtime.getRuntime().availableProcessors() / 2));
    }

    public <T> AsyncJob<T> submit(Command<T> command) {
        UUID id = UUID.randomUUID();
        AsyncJob<T> job = new AsyncJob<>(id, command.name());
        jobs.put(id, job);

        CompletableFuture
                .supplyAsync(() -> {
                    job.markRunning();
                    try {
                        return command.execute();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }, executor)
                .whenComplete((result, throwable) -> {
                    if (throwable != null) {
                        Throwable cause = throwable.getCause() == null ? throwable : throwable.getCause();
                        job.markFailed(cause);
                        LOGGER.error("Command {} failed in async executor", command.name(), cause);
                    } else {
                        job.markCompleted(result);
                        LOGGER.info("Command {} completed asynchronously", command.name());
                    }
                });

        return job;
    }

    public Optional<AsyncJob<?>> findJob(UUID id) {
        return Optional.ofNullable(jobs.get(id));
    }

    @PreDestroy
    public void close() {
        executor.shutdownNow();
    }
}
