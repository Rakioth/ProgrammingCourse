package com.raks.swiftly.application.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raks.swiftly.application.validation.client.CountryTypeCheck;
import com.raks.swiftly.application.validation.client.DocumentCheck;
import com.raks.swiftly.application.validation.client.DocumentTypeCheck;
import com.raks.swiftly.application.validation.client.ViaTypeCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@DocumentCheck
@Data
public class ClientDetailsRequest {

    @DocumentTypeCheck
    @JsonProperty("document_type")
    private String documentTypeCode;

    @JsonProperty("document")
    private String document;

    @CountryTypeCheck
    @JsonProperty("country_type")
    private String countryTypeCode;

    @Pattern(regexp = "(^(\\d[ -]?){8}\\d$)?", message = "error.phone-number.pattern")
    @JsonProperty("phone_number")
    private String phoneNumber;

    @ViaTypeCheck
    @JsonProperty("addr_via")
    private String addrViaCode;

    @NotBlank(message = "error.general.blank")
    @JsonProperty("addr_name")
    private String addrName;

    @JsonProperty("addr_number")
    private Integer addrNumber;

    @NotBlank(message = "error.general.blank")
    @JsonProperty("addr_portal")
    private String addrPortal;

    @Pattern(regexp = "\\d+", message = "error.addr-floor.pattern")
    @JsonProperty("addr_floor")
    private String addrFloor;

    @NotBlank(message = "error.general.blank")
    @JsonProperty("addr_locality")
    private String addrLocality;

    @NotBlank(message = "error.general.blank")
    @JsonProperty("addr_region")
    private String addrRegion;

    @NotBlank(message = "error.general.blank")
    @JsonProperty("addr_postal_code")
    private String addrPostalCode;

}