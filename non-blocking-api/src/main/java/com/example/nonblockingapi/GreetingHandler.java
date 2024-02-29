package com.example.nonblockingapi;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import java.time.Duration;
import java.util.stream.Stream;

@Component
public class GreetingHandler {
    public Flux<String> streamGreetings() {
        return Flux.fromStream(Stream.generate(() -> "Hello @ " + System.currentTimeMillis()))
                    .delayElements(Duration.ofSeconds(1))
                    .take(5); // Emit 5 greetings, 1 second apart
    }
}
