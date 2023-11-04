package com.raks.swiftly.infrastructure.mapper;

import com.raks.swiftly.domain.model.dto.WarningDto;
import com.raks.swiftly.domain.port.EntityMapper;
import com.raks.swiftly.infrastructure.model.entity.Warning;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class})
public interface WarningMapper extends EntityMapper<Warning, WarningDto> {

    WarningMapper INSTANCE = Mappers.getMapper(WarningMapper.class);

}