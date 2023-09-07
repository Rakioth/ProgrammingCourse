package com.raks.swiftly.domain.port.spi;

import com.raks.swiftly.domain.model.helper.ClientRequestDto;
import com.raks.swiftly.domain.model.dto.ClientDto;
import com.raks.swiftly.domain.port.PortCrud;

import java.util.List;

public interface ClientJpaPort extends PortCrud<ClientDto, Long> {

    ClientDto getByUserId(Long id);

    List<ClientDto> getByParameters(ClientRequestDto request);

}