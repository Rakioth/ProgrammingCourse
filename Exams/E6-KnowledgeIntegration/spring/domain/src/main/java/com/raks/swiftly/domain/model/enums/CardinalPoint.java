package com.raks.swiftly.domain.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CardinalPoint {

    NORTH("swiftly.cardinal.north"),
    EAST("swiftly.cardinal.east"),
    SOUTH("swiftly.cardinal.south"),
    WEST("swiftly.cardinal.west");

    @Getter
    private final String ref;

}