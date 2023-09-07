package com.raks.swiftly.domain.port.spi;

import com.raks.swiftly.domain.model.dto.CategoryDto;
import com.raks.swiftly.domain.port.PortCrud;

public interface CategoryJpaPort extends PortCrud<CategoryDto, Long> {

}