package com.raks.swiftly.domain.port.spi;

import com.raks.swiftly.domain.model.dto.StoreDto;
import com.raks.swiftly.domain.port.PortCrud;

public interface StoreJpaPort extends PortCrud<StoreDto, Long> {
}