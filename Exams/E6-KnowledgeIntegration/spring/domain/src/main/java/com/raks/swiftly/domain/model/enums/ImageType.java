package com.raks.swiftly.domain.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ImageType {

    GIF("swiftly.image.gif"),
    JPG("swiftly.image.jpg"),
    PNG("swiftly.image.png"),
    SVG("swiftly.image.svg");

    @Getter
    private final String ref;

}