package com.raks.swiftly.application.controller.request;

import com.raks.swiftly.application.validation.block.BlockCheck;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserRequest {

    @BlockCheck
    @NotNull(message = "{error.date.block}")
    private LocalDateTime releaseBlock;

}