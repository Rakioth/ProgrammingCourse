package com.raks.swiftly.infrastructure.mapper;

import com.raks.swiftly.domain.model.dto.RecoveryTokenDto;
import com.raks.swiftly.domain.port.EntityMapper;
import com.raks.swiftly.infrastructure.model.entity.RecoveryToken;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class})
public interface RecoveryTokenMapper extends EntityMapper<RecoveryToken, RecoveryTokenDto> {

    RecoveryTokenMapper INSTANCE = Mappers.getMapper(RecoveryTokenMapper.class);

}