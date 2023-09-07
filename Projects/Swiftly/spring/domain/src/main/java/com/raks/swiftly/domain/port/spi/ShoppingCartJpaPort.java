package com.raks.swiftly.domain.port.spi;

import com.raks.swiftly.domain.model.dto.ShoppingCartDto;
import com.raks.swiftly.domain.port.PortCrud;

public interface ShoppingCartJpaPort extends PortCrud<ShoppingCartDto, Long> {

    ShoppingCartDto getByUserId(Long id);

}