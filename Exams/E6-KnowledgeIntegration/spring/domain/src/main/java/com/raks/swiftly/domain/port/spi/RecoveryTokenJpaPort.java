package com.raks.swiftly.domain.port.spi;

import com.raks.swiftly.domain.model.dto.RecoveryTokenDto;
import com.raks.swiftly.domain.port.PortCrud;

public interface RecoveryTokenJpaPort extends PortCrud<RecoveryTokenDto, String> {

    RecoveryTokenDto getByEmail(String email);

    void send(RecoveryTokenDto dto);

}