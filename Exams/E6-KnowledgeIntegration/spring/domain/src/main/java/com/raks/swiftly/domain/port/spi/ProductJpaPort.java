package com.raks.swiftly.domain.port.spi;

import com.raks.swiftly.domain.model.dto.ProductDto;
import com.raks.swiftly.domain.model.helper.ProductFilterRequestDto;
import com.raks.swiftly.domain.port.PortCrud;

import java.util.List;

public interface ProductJpaPort extends PortCrud<ProductDto, Long> {

    List<ProductDto> getAllAdmin();

    List<ProductDto> getByParameters(ProductFilterRequestDto request);

}