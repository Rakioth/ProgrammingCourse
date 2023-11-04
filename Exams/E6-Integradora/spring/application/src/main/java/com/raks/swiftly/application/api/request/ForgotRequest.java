package com.raks.swiftly.application.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raks.swiftly.application.validation.login.EmailCheck;
import lombok.Data;

@Data
public class ForgotRequest {

    @EmailCheck
    @JsonProperty("email")
    private String email;

}