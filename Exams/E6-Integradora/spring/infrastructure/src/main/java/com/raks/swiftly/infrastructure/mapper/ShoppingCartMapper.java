package com.raks.swiftly.infrastructure.mapper;

import com.raks.swiftly.domain.model.dto.ShoppingCartDto;
import com.raks.swiftly.domain.port.EntityMapper;
import com.raks.swiftly.infrastructure.model.entity.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {ShoppingCartDetailMapper.class, ClientMapper.class})
public interface ShoppingCartMapper extends EntityMapper<ShoppingCart, ShoppingCartDto> {

    ShoppingCartMapper INSTANCE = Mappers.getMapper(ShoppingCartMapper.class);

}