package com.raks.swiftly.infrastructure.mapper;

import com.raks.swiftly.domain.model.dto.ConfirmationTokenDto;
import com.raks.swiftly.domain.port.EntityMapper;
import com.raks.swiftly.infrastructure.model.entity.ConfirmationToken;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ConfirmationTokenMapper extends EntityMapper<ConfirmationToken, ConfirmationTokenDto> {

    ConfirmationTokenMapper INSTANCE = Mappers.getMapper(ConfirmationTokenMapper.class);

}