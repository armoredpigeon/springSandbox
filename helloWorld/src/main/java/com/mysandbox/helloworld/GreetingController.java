package com.mysandbox.helloworld;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/greet-all")
    public List<Greeting> greetAll(@RequestParam(value="name", defaultValue="Everybody") String name){
        List<Greeting> greetingsList = Stream.of(
            "Shawn",
            "Gus",
            "Jules",
            "Lassy",
            "Woody",
            "Chief Vic",
            "McNab",
            "Mr Yin",
            "Mr Yang",
            "Mary",
            name
        )
        .map((String item) -> {
            return new Greeting(counter.incrementAndGet(), String.format(template, item));
        })
        .collect(Collectors.toList());
        return greetingsList;
    }
}