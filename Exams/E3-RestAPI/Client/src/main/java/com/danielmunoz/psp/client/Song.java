package com.danielmunoz.psp.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Song {
    private Integer   id;
    private String    name;
    private LocalTime time;
    private Integer   albumId;

    @Override
    public String toString() {
        return String.format("Song { id = %s, name = %s, time = %s }", id, name, time);
    }
}
