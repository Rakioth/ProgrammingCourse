package com.raks.swiftly.infrastructure.mapper;

import com.raks.swiftly.domain.model.dto.AddressDto;
import com.raks.swiftly.domain.port.EntityMapper;
import com.raks.swiftly.infrastructure.model.embeddable.Address;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {ViaTypeMapper.class})
public interface AddressMapper extends EntityMapper<Address, AddressDto> {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

}