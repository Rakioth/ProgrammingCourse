package com.raks.swiftly.domain.model.helper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductFilterRequestDto {

    @JsonProperty("model")
    private String     model;

    @JsonProperty("min_price")
    private BigDecimal minPrice;

    @JsonProperty("max_price")
    private BigDecimal maxPrice;

    @JsonProperty("on_sale")
    private Boolean    onSale;

    @JsonProperty("is_new")
    private Boolean    isNew;

    @JsonProperty("cat")
    private String     cat;

}