package com.raks.swiftly.application.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raks.swiftly.domain.model.enums.OrderState;
import lombok.Data;

@Data
public class OrderStateRequest {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("order_state")
    private OrderState state;

}