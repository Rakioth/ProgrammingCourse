package com.raks.swiftly.infrastructure.mapper;

import com.raks.swiftly.domain.model.dto.CoordinateDto;
import com.raks.swiftly.domain.port.EntityMapper;
import com.raks.swiftly.infrastructure.model.embeddable.Coordinate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CoordinateMapper extends EntityMapper<Coordinate, CoordinateDto> {

    CoordinateMapper INSTANCE = Mappers.getMapper(CoordinateMapper.class);

}