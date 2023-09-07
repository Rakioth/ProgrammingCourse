package com.raks.swiftly.infrastructure.mapper;

import com.raks.swiftly.domain.model.dto.CatalogDetailDto;
import com.raks.swiftly.domain.port.EntityMapper;
import com.raks.swiftly.infrastructure.model.embeddable.CatalogDetail;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {ProductMapper.class})
public interface CatalogDetailMapper extends EntityMapper<CatalogDetail, CatalogDetailDto> {

    CatalogDetailMapper INSTANCE = Mappers.getMapper(CatalogDetailMapper.class);

}