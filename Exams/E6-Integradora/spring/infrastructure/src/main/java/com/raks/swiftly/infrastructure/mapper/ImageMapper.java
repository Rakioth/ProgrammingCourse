package com.raks.swiftly.infrastructure.mapper;

import com.raks.swiftly.domain.model.dto.ImageDto;
import com.raks.swiftly.domain.port.EntityMapper;
import com.raks.swiftly.infrastructure.model.embeddable.Image;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImageMapper extends EntityMapper<Image, ImageDto> {

    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

}