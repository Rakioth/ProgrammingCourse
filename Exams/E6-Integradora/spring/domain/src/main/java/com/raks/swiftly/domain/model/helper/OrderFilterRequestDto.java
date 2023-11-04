package com.raks.swiftly.domain.model.helper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raks.swiftly.domain.model.enums.OrderState;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderFilterRequestDto {

    @JsonProperty("user_id")
    private Long          userId;

    @JsonProperty("start_date")
    private LocalDateTime startDate;

    @JsonProperty("end_date")
    private LocalDateTime endDate;

    @JsonProperty("order_state")
    private OrderState    orderState;

    @JsonProperty("min_price")
    private BigDecimal    minPrice;

    @JsonProperty("max_price")
    private BigDecimal    maxPrice;

}