package com.raks.swiftly.domain.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {

    private ViaTypeDto via;
    private String     name;
    private Integer    number;
    private String     portal;
    private String     floor;
    private String     locality;
    private String     region;
    private String     postalCode;

}