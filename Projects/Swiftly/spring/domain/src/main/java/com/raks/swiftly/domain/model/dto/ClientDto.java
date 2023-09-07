package com.raks.swiftly.domain.model.dto;

import com.raks.swiftly.domain.model.enums.Gender;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class ClientDto {
    private Long                id;
    private UserDto             user;
    private Gender              gender;
    private LocalDate           birthdate;
    private CountryTypeDto      countryCode;
    private ClientDocumentDto   documentType;
    private String              document;
    private String              phoneNumber;
    private String              name;
    private String              surnames;
    private AddressDto          address;
    private List<AddressDto>    clientAddresses;
    private List<CreditCardDto> clientCreditCards;
    private BigDecimal          accumulatedExpenses;
    private ClientTypeDto       type;
    private String              comments;
    private Boolean             license;
    private AuditDto            audit;
}