package com.raks.swiftly.application.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raks.swiftly.application.validation.login.RemovedAccount;
import com.raks.swiftly.application.validation.login.UsernameCheck;
import lombok.Data;

@Data
public class LoginRequest {

    @RemovedAccount
    @UsernameCheck
    @JsonProperty("username")
    private String username;

}