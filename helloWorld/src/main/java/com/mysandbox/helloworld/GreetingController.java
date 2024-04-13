package com.mysandbox.helloworld;

import java.util.List;
import java.util.ArrayList;
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
        List<String> namesList = new ArrayList<String>(){{
            add("Shawn");
            add("Gus");
            add("Jules");
            add("Lassy");
            add("Woody");
            add("Chief Vic");
            add("McNab");
            add("Mr Yin");
            add("Mr Yang");
            add("Mary");
        }};
        List<Greeting> greetingsList = new ArrayList<Greeting>();
        namesList.stream().forEach(item -> {
            Greeting tempGreeting = new Greeting(counter.incrementAndGet(), String.format(template, item));
            greetingsList.add(tempGreeting);
        });
        greetingsList.add(new Greeting(counter.incrementAndGet(), String.format(template, name)));
        return greetingsList;
    }
}