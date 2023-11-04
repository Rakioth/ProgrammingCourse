package com.raks.swiftly.domain.port.spi;

import com.raks.swiftly.domain.model.dto.ConfirmationTokenDto;
import com.raks.swiftly.domain.port.PortCrud;

public interface ConfirmationTokenJpaPort extends PortCrud<ConfirmationTokenDto, String> {

    void send(ConfirmationTokenDto dto);

}