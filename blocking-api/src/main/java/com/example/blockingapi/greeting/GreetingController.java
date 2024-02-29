package com.example.blockingapi.greeting;

import org.springframework.web.bind.annotation.GetMapping;

public interface GreetingController {
    @GetMapping("/greeting")
    String greeting();
}