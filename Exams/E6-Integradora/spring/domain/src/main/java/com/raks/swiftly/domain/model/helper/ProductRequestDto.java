package com.raks.swiftly.domain.model.helper;

import com.raks.swiftly.domain.model.enums.ProductRating;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDto {

    private Long          id;
    private String        code;
    private BigDecimal    price;
    private Integer       soldUnits;
    private Integer       stock;
    private Integer       requestThreshold;
    private Integer       hiddenThreshold;
    private Boolean       onSale;
    private Boolean       isNew;
    private ProductRating rating;
    private String        brand;
    private String        model;
    private String        comments;

}