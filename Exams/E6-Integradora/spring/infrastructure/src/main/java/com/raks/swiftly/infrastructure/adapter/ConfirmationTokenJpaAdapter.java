package com.raks.swiftly.infrastructure.adapter;

import com.raks.swiftly.domain.model.dto.EmailDto;
import com.raks.swiftly.domain.port.spi.ConfirmationTokenJpaPort;
import com.raks.swiftly.domain.model.dto.ConfirmationTokenDto;
import com.raks.swiftly.domain.port.spi.EmailJpaPort;
import com.raks.swiftly.infrastructure.mapper.ConfirmationTokenMapper;
import com.raks.swiftly.infrastructure.model.entity.ConfirmationToken;
import com.raks.swiftly.infrastructure.repository.ConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ConfirmationTokenJpaAdapter extends JpaAdapter<ConfirmationToken, ConfirmationTokenDto, String> implements ConfirmationTokenJpaPort {

    private final ConfirmationTokenRepository _confirmationTokenRepository;
    private final EmailJpaPort                _emailJpaPort;

    @Autowired
    public ConfirmationTokenJpaAdapter(ConfirmationTokenRepository confirmationTokenRepository, EmailJpaPort emailJpaPort) {
        super(confirmationTokenRepository, ConfirmationTokenMapper.INSTANCE);
        _confirmationTokenRepository = confirmationTokenRepository;
        _emailJpaPort                = emailJpaPort;
    }

    public void send(ConfirmationTokenDto dto) {
        EmailDto mail = EmailDto.builder()
                                .to(dto.getEmail())
                                .from("dogaufalf@gmail.com")
                                .subject("Verify your email to start using Swiftly")
                                .template("email/code-page")
                                .properties(Map.of("token", dto.getToken()))
                                .build();

        _emailJpaPort.sendEmail(mail);
    }

}