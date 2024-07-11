package com.raks.swiftly.domain.model.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class CatalogDto {

    private Long                   id;
    private SupplierDto            supplier;
    private PeriodDto              period;
    private List<CatalogDetailDto> catalogDetails;

}