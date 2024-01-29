package com.raks.swiftly.infrastructure.model.embeddable;

import com.raks.swiftly.domain.model.enums.ImageType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Image {

    @Column(name = "image_filename", nullable = false)
    private String    filename;

    @Column(name = "image_path", nullable = false)
    private String    path;

    @Column(name = "image_description", nullable = false)
    private String    description;

    @Column(name = "image_filesize", nullable = false)
    private Integer   filesize;

    @Enumerated(EnumType.STRING)
    @Column(name = "image_type", nullable = false)
    private ImageType type;

}