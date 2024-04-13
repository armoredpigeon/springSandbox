package com.mysandbox.helloworld;

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
    public Greeting[] greetAll(@RequestParam(value="name", defaultValue="Everybody") String name){
        String[] namesList = {
            "Shawn",
            "Gus",
            "Jules",
            "Lassy",
            "Woody",
            "Chief Vic",
            "McNab",
            "Mr Yin",
            "Mr Yang",
            "Mary"
        };
        Greeting[] greetingsList = new Greeting[namesList.length + 1];
        for (int i = 0; i<namesList.length; i++){
            Greeting tempGreeting = new Greeting(counter.incrementAndGet(), String.format(template, namesList[i]));
            greetingsList[i] = tempGreeting;
        }
        greetingsList[namesList.length] = new Greeting(counter.incrementAndGet(), String.format(template, name));
        return greetingsList;
    }
}