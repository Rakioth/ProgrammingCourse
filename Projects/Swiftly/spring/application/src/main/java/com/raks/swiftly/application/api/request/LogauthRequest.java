package com.raks.swiftly.application.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raks.swiftly.application.validation.login.BlockAccount;
import com.raks.swiftly.application.validation.login.PasswordCheck;
import lombok.Data;

@BlockAccount
@PasswordCheck
@Data
public class LogauthRequest {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

}