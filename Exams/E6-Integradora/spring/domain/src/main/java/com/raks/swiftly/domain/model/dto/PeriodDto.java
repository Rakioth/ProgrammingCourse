package com.raks.swiftly.domain.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PeriodDto {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

}