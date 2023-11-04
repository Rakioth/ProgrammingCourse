package com.raks.swiftly.infrastructure.mapper;

import com.raks.swiftly.domain.model.dto.PeriodDto;
import com.raks.swiftly.domain.port.EntityMapper;
import com.raks.swiftly.infrastructure.model.embeddable.Period;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PeriodMapper extends EntityMapper<Period, PeriodDto> {

    PeriodMapper INSTANCE = Mappers.getMapper(PeriodMapper.class);

}