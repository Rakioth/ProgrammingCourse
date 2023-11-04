package com.raks.psp.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

@SpringBootApplication
public class RestApplication {

    private static final Logger log = LoggerFactory.getLogger(RestApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String userInput;
            while (true) {
                System.out.printf("%sJoke: %s", "\u001B[36m", "\u001B[0m");
                if ((userInput = br.readLine()).isBlank()) break;

                if (userInput.length() >= 3 && userInput.length() <= 120)
                    jokeQuery(restTemplate, userInput);
                else
                    System.out.printf("%sSearch Query size must be between 3 and 120!%s%n", "\u001B[31m", "\u001B[0m");
            }
        } catch (IOException e) {
            System.err.println("ERROR: I/O Not Found");
        }
        return args -> System.exit(0);
    }

    private static void jokeQuery(RestTemplate restTemplate, String userInput) {
        Jokes jokes = restTemplate.getForObject(String.format("https://api.chucknorris.io/jokes/search?query=%s", userInput), Jokes.class);

        if (jokes.getResult().isEmpty())
            System.out.printf("%sJoke not Found!%s%n", "\u001B[31m", "\u001B[0m");
        else {
            Collections.shuffle(jokes.getResult());
            Joke joke = jokes.getResult().get(0);
            log.info(joke.getValue());
        }
    }

}