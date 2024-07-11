package com.raks.swiftly.domain.model.dto;

import com.raks.swiftly.domain.model.enums.WarningType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class WarningDto {

    private Long        id;
    private String      description;
    private WarningType type;
    private LocalDate   processedDate;
    private UserDto     processedBy;

}