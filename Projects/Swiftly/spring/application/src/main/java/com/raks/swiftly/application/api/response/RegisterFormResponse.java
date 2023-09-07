package com.raks.swiftly.application.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterFormResponse {

    @JsonProperty("step")
    private String step;

    @JsonProperty("data")
    private DataObject data;

    @Data
    @Builder
    public static class DataObject {

        @JsonProperty("email")
        private String email;

    }

}