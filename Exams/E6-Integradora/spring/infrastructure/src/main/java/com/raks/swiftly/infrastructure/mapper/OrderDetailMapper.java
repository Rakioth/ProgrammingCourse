package com.raks.swiftly.infrastructure.mapper;

import com.raks.swiftly.domain.model.dto.OrderDetailDto;
import com.raks.swiftly.domain.port.EntityMapper;
import com.raks.swiftly.infrastructure.model.embeddable.OrderDetail;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {ProductMapper.class})
public interface OrderDetailMapper extends EntityMapper<OrderDetail, OrderDetailDto> {

    OrderDetailMapper INSTANCE = Mappers.getMapper(OrderDetailMapper.class);

}