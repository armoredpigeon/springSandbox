package com.mysandbox.helloworld;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private static final String derogatoryTemplate = "Suck it, %s!";
    private final AtomicLong counter = new AtomicLong();

    private List<Greeting> greetingsList = List.of(
        new Greeting(7L, String.format(template,"Shawn")),
        new Greeting(54L, String.format(template, "Gus")),
        new Greeting(34L, String.format(template, "Jules")),
        new Greeting(48L, String.format(template, "Lassy")),
        new Greeting(32L, String.format(template, "Woody")),
        new Greeting(42L, String.format(template, "Chief Vic")),
        new Greeting(16L, String.format(template, "McNab")),
        new Greeting(64L, String.format(template, "Mr Yin")),
        new Greeting(128L, String.format(template, "Mr Yang")),
        new Greeting(256L, String.format(template, "Mary")),
        new Greeting(512L, String.format(derogatoryTemplate, "Henry"))
    );

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/greet-all")
    public List<Greeting> greetAll(){
        return greetingsList;
    }

    @PostMapping("/add-greeting")
    public ResultData addGreeting(@RequestBody Greeting newGreeting){
        greetingsList.add(newGreeting);

        return new ResultData(200);
    }
}