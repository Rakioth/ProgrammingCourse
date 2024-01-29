package com.raks.swiftly.infrastructure.mapper;

import com.raks.swiftly.domain.model.dto.CatalogDto;
import com.raks.swiftly.domain.port.EntityMapper;
import com.raks.swiftly.infrastructure.model.entity.Catalog;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {SupplierMapper.class, PeriodMapper.class, CatalogDetailMapper.class})
public interface CatalogMapper extends EntityMapper<Catalog, CatalogDto> {

    CatalogMapper INSTANCE = Mappers.getMapper(CatalogMapper.class);

}