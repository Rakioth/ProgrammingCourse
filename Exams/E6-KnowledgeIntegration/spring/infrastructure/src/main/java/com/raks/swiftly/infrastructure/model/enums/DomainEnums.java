package com.raks.swiftly.infrastructure.model.enums;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class DomainEnums {

    @Id
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "ref", nullable = false, unique = true)
    private String ref;

}