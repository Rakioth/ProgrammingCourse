package com.raks.swiftly.domain.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Gender {

    FEMALE("swiftly.gender.female"),
    MALE("swiftly.gender.male"),
    NON_BINARY("swiftly.gender.non-binary"),
    OTHER("swiftly.gender.other");

    @Getter
    private final String ref;

}