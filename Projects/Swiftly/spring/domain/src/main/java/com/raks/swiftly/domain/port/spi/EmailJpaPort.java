package com.raks.swiftly.domain.port.spi;

import com.raks.swiftly.domain.model.dto.EmailDto;

public interface EmailJpaPort {

    void sendEmail(EmailDto dto);

}