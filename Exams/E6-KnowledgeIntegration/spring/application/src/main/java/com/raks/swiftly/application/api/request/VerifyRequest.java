package com.raks.swiftly.application.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raks.swiftly.application.validation.register.CodeCheck;
import lombok.Data;

@CodeCheck
@Data
public class VerifyRequest {

    @JsonProperty("email")
    private String email;

    @JsonProperty("code")
    private String code;

}