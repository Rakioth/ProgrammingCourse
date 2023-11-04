package com.raks.swiftly.domain.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class EmailDto {

    private String              to;
    private String              from;
    private String              subject;
    private String              template;
    private Map<String, Object> properties;

}