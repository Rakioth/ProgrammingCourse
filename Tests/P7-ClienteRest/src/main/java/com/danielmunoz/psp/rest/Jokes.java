package com.danielmunoz.psp.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Jokes {
    private Integer    total;
    private List<Joke> result;
}
