package com.raks.swiftly.infrastructure.mapper;

import com.raks.swiftly.domain.model.dto.ClientTypeDto;
import com.raks.swiftly.domain.port.EntityMapper;
import com.raks.swiftly.infrastructure.model.enums.ClientType;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientTypeMapper extends EntityMapper<ClientType, ClientTypeDto> {

    ClientTypeMapper INSTANCE = Mappers.getMapper(ClientTypeMapper.class);

}