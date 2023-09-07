package com.raks.swiftly.domain.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreDto {
    private Long          id;
    private AddressDto    address;
    private ImageDto      image;
    private CoordinateDto coordinate;
}