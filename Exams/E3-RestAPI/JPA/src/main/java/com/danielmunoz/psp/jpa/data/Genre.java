package com.danielmunoz.psp.jpa.data;

public enum Genre {
    POP("Pop"), ROCK("Rock"), HIPHOP("Hip-Hop"), RAP("Rap"), REGGAETON("Reggaeton"), ELECTRONIC("Electronic"), RNB("R&B");

    private final String _genre;

    Genre(String genre) {
        _genre = genre;
    }

    public String getGenre() {
        return _genre;
    }
}
