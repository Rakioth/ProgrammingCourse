package com.raks.swiftly.domain.port.spi;

import com.raks.swiftly.domain.model.dto.CardTypeDto;
import com.raks.swiftly.domain.port.PortCrud;

public interface CardTypeJpaPort extends PortCrud<CardTypeDto, String> {
}