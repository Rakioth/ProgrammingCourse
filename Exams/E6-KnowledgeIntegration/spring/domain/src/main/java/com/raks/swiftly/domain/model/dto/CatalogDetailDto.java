package com.raks.swiftly.domain.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CatalogDetailDto {

    private ProductDto product;
    private BigDecimal price;

}