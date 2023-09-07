package com.raks.swiftly.domain.model.dto;

import com.raks.swiftly.domain.model.enums.ImageType;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ImageDto {
    private String    filename;
    private String    path;
    private String    description;
    private Integer   filesize;
    private ImageType type;
}