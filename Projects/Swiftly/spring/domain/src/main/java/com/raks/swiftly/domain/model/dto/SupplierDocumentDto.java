package com.raks.swiftly.domain.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SupplierDocumentDto {
    private String code;
    private String ref;
}