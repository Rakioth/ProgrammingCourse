package com.raks.swiftly.infrastructure.mapper;

import com.raks.swiftly.domain.model.dto.SupplierDocumentDto;
import com.raks.swiftly.domain.port.EntityMapper;
import com.raks.swiftly.infrastructure.model.enums.SupplierDocument;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupplierDocumentMapper extends EntityMapper<SupplierDocument, SupplierDocumentDto> {

    SupplierDocumentMapper INSTANCE = Mappers.getMapper(SupplierDocumentMapper.class);

}