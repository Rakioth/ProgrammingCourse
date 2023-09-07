package com.raks.swiftly.domain.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AuditDto {
    private LocalDateTime creationDate;
    private UserDto       adminCreation;
    private LocalDateTime modificationDate;
    private UserDto       adminModification;
    private LocalDateTime deletionDate;
    private UserDto       adminDeletion;
}