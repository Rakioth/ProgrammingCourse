package com.raks.swiftly.infrastructure.mapper;

import com.raks.swiftly.domain.model.dto.StoreDto;
import com.raks.swiftly.domain.port.EntityMapper;
import com.raks.swiftly.infrastructure.model.entity.Store;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {AddressMapper.class, ImageMapper.class, CoordinateMapper.class})
public interface StoreMapper extends EntityMapper<Store, StoreDto> {

    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);

}