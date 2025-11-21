package ro.uvt.books.executor;

import java.time.Instant;
import java.util.UUID;

public class AsyncJob<T> {
    private final UUID id;
    private final String commandName;
    private volatile JobStatus status;
    private volatile T result;
    private volatile String errorMessage;
    private volatile Instant lastUpdated;

    public AsyncJob(UUID id, String commandName) {
        this.id = id;
        this.commandName = commandName;
        this.status = JobStatus.PENDING;
        this.lastUpdated = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public String getCommandName() {
        return commandName;
    }

    public JobStatus getStatus() {
        return status;
    }

    public T getResult() {
        return result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Instant getLastUpdated() {
        return lastUpdated;
    }

    public void markRunning() {
        status = JobStatus.RUNNING;
        lastUpdated = Instant.now();
    }

    public void markCompleted(T result) {
        this.result = result;
        status = JobStatus.COMPLETED;
        lastUpdated = Instant.now();
    }

    public void markFailed(Throwable e) {
        status = JobStatus.FAILED;
        errorMessage = e.getMessage();
        lastUpdated = Instant.now();
    }
}
