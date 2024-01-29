package com.raks.swiftly.infrastructure.mapper;

import com.raks.swiftly.domain.model.dto.AuditDto;
import com.raks.swiftly.domain.port.EntityMapper;
import com.raks.swiftly.infrastructure.model.embeddable.Audit;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class})
public interface AuditMapper extends EntityMapper<Audit, AuditDto> {

    AuditMapper INSTANCE = Mappers.getMapper(AuditMapper.class);

}