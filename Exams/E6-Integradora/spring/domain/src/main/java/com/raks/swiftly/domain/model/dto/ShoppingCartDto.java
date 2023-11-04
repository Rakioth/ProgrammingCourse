package com.raks.swiftly.domain.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ShoppingCartDto {

    private Long                        id;
    private LocalDateTime               creationDate;
    private List<ShoppingCartDetailDto> shoppingCartDetails;
    private BigDecimal                  price;
    private ClientDto                   client;

}