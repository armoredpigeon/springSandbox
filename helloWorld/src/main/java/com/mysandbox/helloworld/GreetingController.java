package com.mysandbox.helloworld;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private static final String derogatoryTemplate = "Suck it, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/greet-all")
    public List<Greeting> greetAll(@RequestParam(value="name", defaultValue="Everybody") String name){
        List<Greeting> greetingsList = Map.ofEntries(
            Map.entry(7L, "Shawn"),
            Map.entry(54L, "Gus"),
            Map.entry(34L, "Jules"),
            Map.entry(48L, "Lassy"),
            Map.entry(32L, "Woody"),
            Map.entry(42L, "Chief Vic"),
            Map.entry(16L, "McNab"),
            Map.entry(64L, "Mr Yin"),
            Map.entry(128L, "Mr Yang"),
            Map.entry(256L, "Mary"),
            Map.entry(512L, "Henry"),
            Map.entry(1024L, name)
        ).entrySet().stream()
        .map((Map.Entry item) -> {
            var givenKey = (long) item.getKey();
            var givenVal = item.getValue();
            return new Greeting(givenKey, String.format(givenVal=="Henry" ? derogatoryTemplate : template, givenVal));
        })
        .collect(Collectors.toList());
        return greetingsList;
    }
}