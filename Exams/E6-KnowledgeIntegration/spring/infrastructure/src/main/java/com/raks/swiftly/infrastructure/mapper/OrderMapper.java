package com.raks.swiftly.infrastructure.mapper;

import com.raks.swiftly.domain.model.dto.OrderDto;
import com.raks.swiftly.domain.port.EntityMapper;
import com.raks.swiftly.infrastructure.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {OrderDetailMapper.class, ClientMapper.class, UserMapper.class})
public interface OrderMapper extends EntityMapper<Order, OrderDto> {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

}