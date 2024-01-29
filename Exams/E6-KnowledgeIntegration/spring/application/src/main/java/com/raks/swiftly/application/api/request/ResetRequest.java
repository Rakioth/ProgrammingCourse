package com.raks.swiftly.application.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raks.swiftly.application.validation.reset.PasswordMatch;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@PasswordMatch
@Data
public class ResetRequest {

    @Size(min = 6, max = 12, message = "error.password.size")
    @Pattern(regexp = "^(?=\\S+$)(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!#$%&]).*", message = "error.password.pattern")
    @JsonProperty("password")
    private String password;

    @JsonProperty("password_confirm")
    private String passwordConfirm;

}