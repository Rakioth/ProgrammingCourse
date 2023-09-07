package com.raks.swiftly.infrastructure.model.embeddable;

import com.raks.swiftly.domain.model.enums.CardinalPoint;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Coordinate {

    @Column(name = "latitude_degrees", nullable = false)
    private Integer latitudeDegrees;

    @Column(name = "latitude_minutes", nullable = false)
    private Integer latitudeMinutes;

    @Column(name = "latitude_seconds", nullable = false)
    private Integer latitudeSeconds;

    @Enumerated(EnumType.STRING)
    @Column(name = "latitude_cardinal_point", nullable = false)
    private CardinalPoint latitudeCardinalPoint;

    @Column(name = "longitude_degrees", nullable = false)
    private Integer longitudeDegrees;

    @Column(name = "longitude_minutes", nullable = false)
    private Integer longitudeMinutes;

    @Column(name = "longitude_seconds", nullable = false)
    private Integer longitudeSeconds;

    @Enumerated(EnumType.STRING)
    @Column(name = "longitude_cardinal_point", nullable = false)
    private CardinalPoint longitudeCardinalPoint;

}