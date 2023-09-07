package com.raks.swiftly.infrastructure.mapper;

import com.raks.swiftly.domain.model.dto.ProductDto;
import com.raks.swiftly.domain.port.EntityMapper;
import com.raks.swiftly.infrastructure.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {ImageMapper.class, AuditMapper.class})
public interface ProductMapper extends EntityMapper<Product, ProductDto> {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

}