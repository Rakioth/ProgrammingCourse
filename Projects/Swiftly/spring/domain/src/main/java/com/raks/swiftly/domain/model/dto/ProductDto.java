package com.raks.swiftly.domain.model.dto;

import com.raks.swiftly.domain.model.enums.ProductRating;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ProductDto {
    private Long           id;
    private String         code;
    private String         description;
    private BigDecimal     price;
    private Integer        soldUnits;
    private BigDecimal     accumulatedExpenses;
    private Integer        stock;
    private Integer        requestThreshold;
    private Integer        hiddenThreshold;
    private Boolean        onSale;
    private Boolean        isNew;
    private ProductRating  rating;
    private String         brand;
    private String         model;
    private List<ImageDto> productImages;
    private String         comments;
    private AuditDto       audit;
    private String         cat;
}