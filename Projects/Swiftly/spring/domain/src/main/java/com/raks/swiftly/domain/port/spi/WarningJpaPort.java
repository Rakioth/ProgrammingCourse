package com.raks.swiftly.domain.port.spi;

import com.raks.swiftly.domain.model.dto.WarningDto;
import com.raks.swiftly.domain.port.PortCrud;

import java.util.List;

public interface WarningJpaPort extends PortCrud<WarningDto, Long> {

    List<WarningDto> getUnprocessed();

}