package com.raks.swiftly.domain.model.dto;

import com.raks.swiftly.domain.model.enums.OrderState;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderDto {
    private Long                 id;
    private LocalDateTime        date;
    private List<OrderDetailDto> orderDetails;
    private BigDecimal           totalPrice;
    private ClientDto            client;
    private OrderState           state;
    private UserDto              processedBy;
}