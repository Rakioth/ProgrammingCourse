package com.raks.swiftly.infrastructure.mapper;

import com.raks.swiftly.domain.model.dto.CardTypeDto;
import com.raks.swiftly.domain.port.EntityMapper;
import com.raks.swiftly.infrastructure.model.enums.CardType;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardTypeMapper extends EntityMapper<CardType, CardTypeDto> {

    CardTypeMapper INSTANCE = Mappers.getMapper(CardTypeMapper.class);

}