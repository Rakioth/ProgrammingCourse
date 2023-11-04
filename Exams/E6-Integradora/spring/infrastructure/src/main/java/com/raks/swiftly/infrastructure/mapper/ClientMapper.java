package com.raks.swiftly.infrastructure.mapper;

import com.raks.swiftly.domain.model.dto.ClientDto;
import com.raks.swiftly.domain.port.EntityMapper;
import com.raks.swiftly.infrastructure.model.entity.Client;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class, CountryTypeMapper.class, ClientDocumentMapper.class, AddressMapper.class, ClientTypeMapper.class, CreditCardMapper.class, AuditMapper.class})
public interface ClientMapper extends EntityMapper<Client, ClientDto> {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

}