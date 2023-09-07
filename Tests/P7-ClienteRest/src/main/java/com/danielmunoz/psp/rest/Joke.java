package com.danielmunoz.psp.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.net.URL;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Joke {
    private List<String> categories;
    private String       createdAt;
    private URL          iconUrl;
    private String       id;
    private String       updatedAt;
    private URL          url;
    private String       value;
}
