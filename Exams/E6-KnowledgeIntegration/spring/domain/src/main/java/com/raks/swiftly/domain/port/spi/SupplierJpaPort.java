package com.raks.swiftly.domain.port.spi;

import com.raks.swiftly.domain.model.dto.SupplierDto;
import com.raks.swiftly.domain.port.PortCrud;

public interface SupplierJpaPort extends PortCrud<SupplierDto, Long> {
}