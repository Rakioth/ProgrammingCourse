package com.raks.swiftly.infrastructure.mapper;

import com.raks.swiftly.domain.model.dto.CountryTypeDto;
import com.raks.swiftly.domain.port.EntityMapper;
import com.raks.swiftly.infrastructure.model.enums.CountryType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CountryTypeMapper extends EntityMapper<CountryType, CountryTypeDto> {

    CountryTypeMapper INSTANCE = Mappers.getMapper(CountryTypeMapper.class);

}