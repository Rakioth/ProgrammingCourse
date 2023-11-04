package com.raks.swiftly.domain.model.dto;

import com.raks.swiftly.domain.model.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDto {

    private Long          id;
    private String        username;
    private String        email;
    private String        password;
    private Role          role;
    private Integer       successAuth;
    private Integer       failedAuth;
    private LocalDateTime lastConnection;
    private LocalDateTime releaseBlock;

}