package com.raks.swiftly.infrastructure.adapter;

import com.raks.swiftly.domain.exception.EntityNotFoundException;
import com.raks.swiftly.domain.model.dto.RecoveryTokenDto;
import com.raks.swiftly.domain.model.dto.EmailDto;
import com.raks.swiftly.domain.port.spi.RecoveryTokenJpaPort;
import com.raks.swiftly.domain.port.spi.EmailJpaPort;
import com.raks.swiftly.infrastructure.mapper.RecoveryTokenMapper;
import com.raks.swiftly.infrastructure.model.entity.RecoveryToken;
import com.raks.swiftly.infrastructure.repository.RecoveryTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RecoveryTokenJpaAdapter extends JpaAdapter<RecoveryToken, RecoveryTokenDto, String> implements RecoveryTokenJpaPort {

    private final RecoveryTokenRepository _recoveryTokenRepository;
    private final RecoveryTokenMapper     _recoveryTokenMapper;
    private final EmailJpaPort            _emailJpaPort;

    @Autowired
    public RecoveryTokenJpaAdapter(RecoveryTokenRepository recoveryTokenRepository, EmailJpaPort emailJpaPort) {
        super(recoveryTokenRepository, RecoveryTokenMapper.INSTANCE);
        _recoveryTokenRepository = recoveryTokenRepository;
        _recoveryTokenMapper     = RecoveryTokenMapper.INSTANCE;
        _emailJpaPort            = emailJpaPort;
    }

    @Override
    public RecoveryTokenDto getByEmail(String email) {
        return _recoveryTokenMapper.toDto(
                _recoveryTokenRepository.findByUser_Email(email)
                                        .orElseThrow(EntityNotFoundException::new)
        );
    }

    @Override
    public void send(RecoveryTokenDto dto) {
        EmailDto mail = EmailDto.builder()
                                .to(dto.getUser().getEmail())
                                .from("dogaufalf@gmail.com")
                                .subject("Reset your Swiftly password")
                                .template("email/recover-page")
                                .properties(Map.of("token", dto.getToken(),
                                                   "email", dto.getUser().getEmail()))
                                .build();

        _emailJpaPort.sendEmail(mail);
    }

}