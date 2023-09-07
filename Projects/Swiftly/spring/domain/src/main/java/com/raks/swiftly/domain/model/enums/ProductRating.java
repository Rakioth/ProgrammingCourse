package com.raks.swiftly.domain.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ProductRating {
    ONE("swiftly.rating.one"),
    TWO("swiftly.rating.two"),
    THREE("swiftly.rating.three"),
    FOUR("swiftly.rating.four"),
    FIVE("swiftly.rating.five");

    @Getter
    private final String ref;

}