package com.raks.swiftly.domain.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShoppingCartDetailDto {

    private ProductDto product;
    private Integer    units;

}