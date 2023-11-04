package com.raks.swiftly.domain.port.spi;

import com.raks.swiftly.domain.model.dto.*;

public interface AuthJpaPort {

    void register(ConfirmationTokenDto dto);

    void verify(ConfirmationTokenDto dto);

    TokenDto auth(UserDto dto);

    TokenDto login(UserDto dto);

    void forgot(String email);

    void reset(RecoveryTokenDto dto);

    void createClient(ClientDto dto, String accessToken);

    String refresh(String refreshToken);

    void resendConfirmation(String email);

    void resendRecovery(String email);

}