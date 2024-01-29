package com.raks.swiftly.domain.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class PromotionDto {

    private Long             id;
    private String           description;
    private PeriodDto        period;
    private List<ProductDto> products;
    private BigDecimal       discount;
    private AuditDto         audit;

}