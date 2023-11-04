package com.raks.swiftly.infrastructure.mapper;

import com.raks.swiftly.domain.model.dto.CategoryDto;
import com.raks.swiftly.domain.port.EntityMapper;
import com.raks.swiftly.infrastructure.model.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {AuditMapper.class, ClientMapper.class, ProductMapper.class, StoreMapper.class})
public interface CategoryMapper extends EntityMapper<Category, CategoryDto> {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

}