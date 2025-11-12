package ro.uvt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.uvt.difexamples.ClientComponent;

@RestController
public class HelloController {

    private final ClientComponent clientComponent;

    @Autowired
    public HelloController(ClientComponent clientComponent) {
        this.clientComponent = clientComponent;
        System.out.println("HelloController created with ClientComponent = " + clientComponent);
    }

    @GetMapping("/")
    public String hello() {
        return "Hello from Spring";
    }

    @GetMapping("/client")
    public String clientInfo() {
        return "Hello from ClientComponent = " + clientComponent;
    }
}
