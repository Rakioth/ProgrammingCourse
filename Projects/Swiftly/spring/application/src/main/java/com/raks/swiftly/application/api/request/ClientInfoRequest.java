package com.raks.swiftly.application.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raks.swiftly.application.validation.client.BirthdateCheck;
import com.raks.swiftly.application.validation.client.GenderCheck;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientInfoRequest {

    @NotBlank(message = "error.general.blank")
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "error.general.blank")
    @JsonProperty("surnames")
    private String surnames;

    @GenderCheck
    @JsonProperty("gender_code")
    private String genderCode;

    @BirthdateCheck
    @JsonProperty("birthdate")
    private LocalDate birthdate;

}