package com.raks.swiftly.domain.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SupplierDto {

    private Long                id;
    private SupplierDocumentDto documentType;
    private String              document;
    private String              landline;
    private String              phoneNumber;
    private String              name;
    private AddressDto          address;
    private SupplierTypeDto     type;
    private String              comments;
    private AuditDto            audit;

}