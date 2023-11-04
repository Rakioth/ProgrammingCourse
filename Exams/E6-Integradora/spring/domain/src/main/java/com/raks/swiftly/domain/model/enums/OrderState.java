package com.raks.swiftly.domain.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderState {

    BEING_PREPARED("swiftly.order.preparing"),
    IN_TRANSIT("swiftly.order.transit"),
    COMPLETED("swiftly.order.completed"),
    LOST("swiftly.order.lost");

    @Getter
    private final String ref;

}