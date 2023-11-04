package com.raks.swiftly.domain.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Set;

@RequiredArgsConstructor
public enum Role {

    USER(Collections.emptySet()),
    CLIENT(Set.of(
            Permission.CLIENT_CREATE,
            Permission.CLIENT_READ,
            Permission.CLIENT_UPDATE,
            Permission.CLIENT_DELETE
    )),
    ADMIN(Set.of(
            Permission.ADMIN_CREATE,
            Permission.ADMIN_READ,
            Permission.ADMIN_UPDATE,
            Permission.ADMIN_DELETE,
            Permission.CLIENT_CREATE,
            Permission.CLIENT_READ,
            Permission.CLIENT_UPDATE,
            Permission.CLIENT_DELETE
    )),
    DELETED(Collections.emptySet());

    @Getter
    private final Set<Permission> permissions;

}