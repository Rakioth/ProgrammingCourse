package com.raks.swiftly.domain.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum WarningType {
    LOW("swiftly.warning.low"),
    MEDIUM("swiftly.warning.medium"),
    HIGH("swiftly.warning.high");

    @Getter
    private final String ref;

}