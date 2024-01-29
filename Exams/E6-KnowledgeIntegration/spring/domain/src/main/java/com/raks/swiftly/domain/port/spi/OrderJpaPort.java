package com.raks.swiftly.domain.port.spi;

import com.raks.swiftly.domain.model.dto.OrderDto;
import com.raks.swiftly.domain.model.helper.OrderFilterRequestDto;
import com.raks.swiftly.domain.port.PortCrud;

import java.util.List;

public interface OrderJpaPort extends PortCrud<OrderDto, Long> {

    List<OrderDto> getByUserId(Long id);

    List<OrderDto> getByParameters(OrderFilterRequestDto request);

}