package ro.uvt.books.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.uvt.books.commands.*;
import ro.uvt.books.executor.AsyncCommandExecutor;
import ro.uvt.books.executor.AsyncJob;
import ro.uvt.books.executor.CommandExecutor;
import ro.uvt.books.executor.JobStatus;
import ro.uvt.books.model.Book;
import ro.uvt.books.model.BookRequest;
import ro.uvt.books.observer.AllBooksSubject;
import ro.uvt.books.service.BooksService;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;
    private final CommandExecutor commandExecutor;
    private final AsyncCommandExecutor asyncCommandExecutor;
    private final AllBooksSubject allBooksSubject;

    public BooksController(BooksService booksService,
                           CommandExecutor commandExecutor,
                           AsyncCommandExecutor asyncCommandExecutor,
                           AllBooksSubject allBooksSubject) {
        this.booksService = booksService;
        this.commandExecutor = commandExecutor;
        this.asyncCommandExecutor = asyncCommandExecutor;
        this.allBooksSubject = allBooksSubject;
    }

    @GetMapping
    public List<Book> getBooks() {
        return commandExecutor.execute(new GetAllBooksCommand(booksService));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable long id) {
        Optional<Book> book = commandExecutor.execute(new GetBookByIdCommand(booksService, id));
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody BookRequest request) {
        AsyncJob<Book> job = asyncCommandExecutor.submit(new CreateBookCommand(booksService, request, allBooksSubject));

        URI statusUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/requests/{id}")
                .buildAndExpand(job.getId())
                .toUri();

        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("requestId", job.getId());
        payload.put("status", job.getStatus());
        payload.put("statusUrl", statusUri.toString());
        payload.put("message", "Book creation accepted and queued");

        return ResponseEntity.accepted().location(statusUri).body(payload);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> replaceBook(@PathVariable long id, @RequestBody BookRequest request) {
        Optional<Book> updated = commandExecutor.execute(new UpdateBookCommand(booksService, id, request));
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable long id) {
        boolean deleted = commandExecutor.execute(new DeleteBookCommand(booksService, id));
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/requests/{requestId}")
    public ResponseEntity<?> requestStatus(@PathVariable UUID requestId) {
        Optional<AsyncJob<?>> optionalJob = asyncCommandExecutor.findJob(requestId);
        if (optionalJob.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        AsyncJob<?> job = optionalJob.get();
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("requestId", job.getId());
        payload.put("command", job.getCommandName());
        payload.put("status", job.getStatus());
        payload.put("lastUpdated", job.getLastUpdated());

        HttpStatus status;
        if (job.getStatus() == JobStatus.COMPLETED) {
            payload.put("result", job.getResult());
            status = HttpStatus.OK;
        } else if (job.getStatus() == JobStatus.FAILED) {
            payload.put("error", job.getErrorMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            status = HttpStatus.ACCEPTED;
        }

        return ResponseEntity.status(status).body(payload);
    }
}
