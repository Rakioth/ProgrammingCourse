package com.raks.swiftly.infrastructure.mapper;

import com.raks.swiftly.domain.model.dto.SupplierTypeDto;
import com.raks.swiftly.domain.port.EntityMapper;
import com.raks.swiftly.infrastructure.model.enums.SupplierType;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupplierTypeMapper extends EntityMapper<SupplierType, SupplierTypeDto> {

    SupplierTypeMapper INSTANCE = Mappers.getMapper(SupplierTypeMapper.class);

}