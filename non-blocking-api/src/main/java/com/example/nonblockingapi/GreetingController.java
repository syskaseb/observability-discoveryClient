package com.example.nonblockingapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class GreetingController {

    private final GreetingHandler greetingHandler;

    public GreetingController(GreetingHandler greetingHandler) {
        this.greetingHandler = greetingHandler;
    }

    @GetMapping("/hello")
    public Flux<String> hello() {
        return greetingHandler.streamGreetings();
    }

}