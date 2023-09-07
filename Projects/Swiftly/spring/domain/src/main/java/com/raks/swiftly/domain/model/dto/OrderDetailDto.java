package com.raks.swiftly.domain.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrderDetailDto {
    private ProductDto product;
    private Integer    units;
    private BigDecimal price;
}