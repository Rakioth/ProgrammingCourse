package com.raks.swiftly.infrastructure.mapper;

import com.raks.swiftly.domain.model.dto.ShoppingCartDetailDto;
import com.raks.swiftly.domain.port.EntityMapper;
import com.raks.swiftly.infrastructure.model.embeddable.ShoppingCartDetail;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {ProductMapper.class})
public interface ShoppingCartDetailMapper extends EntityMapper<ShoppingCartDetail, ShoppingCartDetailDto> {

    ShoppingCartDetailMapper INSTANCE = Mappers.getMapper(ShoppingCartDetailMapper.class);

}