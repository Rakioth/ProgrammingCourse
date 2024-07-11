package com.raks.jpa.dto

enum class GenreDto(
    private val genre: String
) {

    POP("Pop"),
    ROCK("Rock"),
    HIPHOP("Hip-Hop"),
    RAP("Rap"),
    REGGAETON("Reggaeton"),
    ELECTRONIC("Electronic"),
    RNB("R&B");

}