package com.raks.swiftly.infrastructure.mapper;

import com.raks.swiftly.domain.model.dto.SupplierDto;
import com.raks.swiftly.domain.port.EntityMapper;
import com.raks.swiftly.infrastructure.model.entity.Supplier;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {SupplierDocumentMapper.class, AddressMapper.class, SupplierTypeMapper.class, AuditMapper.class})
public interface SupplierMapper extends EntityMapper<Supplier, SupplierDto> {

    SupplierMapper INSTANCE = Mappers.getMapper(SupplierMapper.class);

}