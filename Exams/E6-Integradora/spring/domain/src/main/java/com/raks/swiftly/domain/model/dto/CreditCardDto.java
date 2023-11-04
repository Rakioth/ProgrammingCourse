package com.raks.swiftly.domain.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CreditCardDto {

    private CardTypeDto type;
    private String      number;
    private String      ccv;
    private LocalDate   expiredDate;

}