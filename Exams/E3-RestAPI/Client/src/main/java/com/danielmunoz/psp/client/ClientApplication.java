package com.danielmunoz.psp.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;
import java.util.Arrays;

@SpringBootApplication
public class ClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) {
        return args -> {
            Song song = restTemplate.getForObject("http://localhost:8080/songs/17", Song.class);
            System.out.printf("%s=== GET ===%s%n%s%n", ConsoleColors.ANSI_GREEN, ConsoleColors.ANSI_RESET, song);

            Song newSong = new Song();
            newSong.setName("Heartbeat");
            newSong.setTime(LocalTime.of(0, 2, 57));
            newSong.setAlbumId(1);

            song = restTemplate.postForObject("http://localhost:8080/songs", newSong, Song.class);
            System.out.printf("%s=== POST ===%s%n%s%n", ConsoleColors.ANSI_YELLOW, ConsoleColors.ANSI_RESET, song);

            Song updatedSong = new Song();
            updatedSong.setName("Medusa");
            updatedSong.setTime(LocalTime.of(0, 1, 35));
            updatedSong.setAlbumId(2);

            restTemplate.put(String.format("http://localhost:8080/songs/%s", song.getId()), updatedSong, Song.class);
            song = restTemplate.getForObject(String.format("http://localhost:8080/songs/%s", song.getId()), Song.class);
            System.out.printf("%s=== PUT ===%s%n%s%n", ConsoleColors.ANSI_BLUE, ConsoleColors.ANSI_RESET, song);

            restTemplate.delete(String.format("http://localhost:8080/songs/%s", song.getId()));
            System.out.printf("%s=== DELETE ===%s%nSong with ID %s Removed!%n", ConsoleColors.ANSI_RED, ConsoleColors.ANSI_RESET, song.getId());

            Song[] songs = restTemplate.getForObject("http://localhost:8080/songs", Song[].class);
            System.out.printf("%s=== GETALL ===%s%n%s%n", ConsoleColors.ANSI_PURPLE, ConsoleColors.ANSI_RESET, Arrays.toString(songs));

            System.exit(0);
        };
    }
}
