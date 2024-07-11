package com.raks.swiftly.infrastructure.model.embeddable;

import com.raks.swiftly.infrastructure.model.enums.ViaType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Address {

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "addr_via", nullable = false)
    private ViaType via;

    @Column(name = "addr_name")
    private String  name;

    @Column(name = "addr_number")
    private Integer number;

    @Column(name = "addr_portal")
    private String  portal;

    @Column(name = "addr_floor")
    private String  floor;

    @Column(name = "addr_locality", nullable = false)
    private String  locality;

    @Column(name = "addr_region", nullable = false)
    private String  region;

    @Column(name = "addr_postal_code", nullable = false)
    private String  postalCode;

}