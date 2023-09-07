package com.raks.swiftly.application.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ClientFormResponse {

    @JsonProperty("step_number")
    private Integer stepNumber;

    @JsonProperty("step")
    private String step;

    @JsonProperty("data")
    private DataObject data;

    @Data
    @Builder
    public static class DataObject {

        @JsonProperty("name")
        private String name;

        @JsonProperty("surnames")
        private String surnames;

        @JsonProperty("gender_code")
        private String genderCode;

        @JsonProperty("birthdate")
        private LocalDate birthdate;

        @JsonProperty("document_type")
        private String documentTypeCode;

        @JsonProperty("document")
        private String document;

        @JsonProperty("country_type")
        private String countryTypeCode;

        @JsonProperty("phone_number")
        private String phoneNumber;

        @JsonProperty("addr_via")
        private String addrViaCode;

        @JsonProperty("addr_name")
        private String addrName;

        @JsonProperty("addr_number")
        private Integer addrNumber;

        @JsonProperty("addr_portal")
        private String addrPortal;

        @JsonProperty("addr_floor")
        private String addrFloor;

        @JsonProperty("addr_locality")
        private String addrLocality;

        @JsonProperty("addr_region")
        private String addrRegion;

        @JsonProperty("addr_postal_code")
        private String addrPostalCode;

        @JsonProperty("card_type")
        private String cardTypeCode;

        @JsonProperty("card_number")
        private String cardNumber;

        @JsonProperty("card_ccv")
        private String cardCcv;

        @JsonProperty("card_expired_date")
        private LocalDate cardExpiredDate;

        @JsonProperty("comments")
        private String comments;

        @JsonProperty("license")
        private Boolean license;

    }

}