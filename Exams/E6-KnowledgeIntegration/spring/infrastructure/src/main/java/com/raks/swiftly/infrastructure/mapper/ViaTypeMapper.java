package com.raks.swiftly.infrastructure.mapper;

import com.raks.swiftly.domain.model.dto.ViaTypeDto;
import com.raks.swiftly.domain.port.EntityMapper;
import com.raks.swiftly.infrastructure.model.enums.ViaType;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ViaTypeMapper extends EntityMapper<ViaType, ViaTypeDto> {

    ViaTypeMapper INSTANCE = Mappers.getMapper(ViaTypeMapper.class);

}