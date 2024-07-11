package com.raks.swiftly.domain.port.spi;

import com.raks.swiftly.domain.model.dto.CatalogDto;
import com.raks.swiftly.domain.port.PortCrud;

public interface CatalogJpaPort extends PortCrud<CatalogDto, Long> {

    CatalogDto getBySupplierId(Long id);

}