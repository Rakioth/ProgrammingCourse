package com.raks.swiftly.infrastructure.mapper;

import com.raks.swiftly.domain.model.dto.ClientDocumentDto;
import com.raks.swiftly.domain.port.EntityMapper;
import com.raks.swiftly.infrastructure.model.enums.ClientDocument;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientDocumentMapper extends EntityMapper<ClientDocument, ClientDocumentDto> {

    ClientDocumentMapper INSTANCE = Mappers.getMapper(ClientDocumentMapper.class);

}