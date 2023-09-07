package com.raks.swiftly.domain.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecoveryTokenDto {
    private String  token;
    private UserDto user;
}