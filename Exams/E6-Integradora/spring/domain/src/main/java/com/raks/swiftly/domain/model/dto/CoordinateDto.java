package com.raks.swiftly.domain.model.dto;

import com.raks.swiftly.domain.model.enums.CardinalPoint;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoordinateDto {

    private Integer       latitudeDegrees;
    private Integer       latitudeMinutes;
    private Integer       latitudeSeconds;
    private CardinalPoint latitudeCardinalPoint;
    private Integer       longitudeDegrees;
    private Integer       longitudeMinutes;
    private Integer       longitudeSeconds;
    private CardinalPoint longitudeCardinalPoint;

}