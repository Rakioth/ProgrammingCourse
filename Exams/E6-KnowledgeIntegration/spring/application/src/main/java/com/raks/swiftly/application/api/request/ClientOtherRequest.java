package com.raks.swiftly.application.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raks.swiftly.application.validation.client.CardTypeCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientOtherRequest {

    @CardTypeCheck
    @JsonProperty("card_type")
    private String    cardTypeCode;

    @Pattern(regexp = "\\d+", message = "error.card-number.pattern")
    @JsonProperty("card_number")
    private String    cardNumber;

    @Pattern(regexp = "\\d+", message = "error.card-ccv.pattern")
    @JsonProperty("card_ccv")
    private String    cardCcv;

    @JsonProperty("card_expired_date")
    private LocalDate cardExpiredDate;

    @NotBlank(message = "error.general.blank")
    @JsonProperty("comments")
    private String    comments;

    @NotNull(message = "error.license.check")
    @JsonProperty("license")
    private Boolean   license;

}