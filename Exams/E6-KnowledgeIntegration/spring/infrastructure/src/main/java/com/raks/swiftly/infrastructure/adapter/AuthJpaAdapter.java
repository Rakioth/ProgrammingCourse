package com.raks.swiftly.infrastructure.adapter;

import com.raks.swiftly.domain.model.dto.*;
import com.raks.swiftly.domain.model.enums.Role;
import com.raks.swiftly.domain.port.spi.*;
import com.raks.swiftly.infrastructure.configuration.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthJpaAdapter implements AuthJpaPort {

    private final UserJpaPort              _userJpaPort;
    private final ClientJpaAdapter         _clientJpaAdapter;
    private final ShoppingCartJpaPort      _shoppingCartJpaPort;
    private final PasswordEncoder          _passwordEncoder;
    private final JwtService               _jwtService;
    private final AuthenticationManager    _authenticationManager;
    private final ConfirmationTokenJpaPort _confirmationTokenJpaPort;
    private final RecoveryTokenJpaPort     _recoveryTokenJpaPort;

    @Override
    public void register(ConfirmationTokenDto dto) {
        _confirmationTokenJpaPort.send(dto);
        _confirmationTokenJpaPort.create(dto);
    }

    @Override
    public void verify(ConfirmationTokenDto dto) {
        ConfirmationTokenDto confirmationTokenDto = _confirmationTokenJpaPort.read(dto.getEmail());
        confirmationTokenDto.setVerified(true);
        _confirmationTokenJpaPort.update(confirmationTokenDto);
    }

    @Override
    public TokenDto auth(UserDto dto) {
        dto.setPassword(_passwordEncoder.encode(dto.getPassword()));
        _userJpaPort.create(dto);

        UserDto userDto = _userJpaPort.getByUsername(dto.getUsername());
        userDto.setLastConnection(LocalDateTime.now());
        userDto.setSuccessAuth(userDto.getSuccessAuth() + 1);
        userDto.setFailedAuth(0);
        userDto.setReleaseBlock(null);
        _userJpaPort.update(userDto);

        return _jwtService.createJwt(dto.getUsername());
    }

    @Override
    public TokenDto login(UserDto dto) {
        _authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUsername(),
                        dto.getPassword()
                )
        );

        UserDto userDto = _userJpaPort.getByUsername(dto.getUsername());
        userDto.setLastConnection(LocalDateTime.now());
        userDto.setSuccessAuth(userDto.getSuccessAuth() + 1);
        userDto.setFailedAuth(0);
        userDto.setReleaseBlock(null);
        _userJpaPort.update(userDto);

        return _jwtService.createJwt(dto.getUsername());
    }

    @Override
    public void forgot(String email) {
        String  token   = UUID.randomUUID().toString().replace("-", "");
        UserDto userDto = _userJpaPort.getByEmail(email);

        RecoveryTokenDto dto = RecoveryTokenDto.builder()
                                               .token(token)
                                               .user(userDto)
                                               .build();

        _recoveryTokenJpaPort.send(dto);
        _recoveryTokenJpaPort.create(dto);
    }

    @Override
    public void reset(RecoveryTokenDto dto) {
        UserDto userDto = _recoveryTokenJpaPort.read(dto.getToken()).getUser();
        _recoveryTokenJpaPort.delete(dto.getToken());
        userDto.setPassword(_passwordEncoder.encode(dto.getUser().getPassword()));
        _userJpaPort.update(userDto);
    }

    @Override
    public void createClient(ClientDto dto, String accessToken) {
        UserDto userDto = _userJpaPort.getByAccessToken(accessToken);
        userDto.setRole(Role.CLIENT);
        _userJpaPort.update(userDto);
        dto.setUser(userDto);
        ClientDto clientDto = _clientJpaAdapter.create(dto);
        _shoppingCartJpaPort.create(
                ShoppingCartDto.builder()
                               .creationDate(LocalDateTime.now())
                               .price(BigDecimal.ZERO)
                               .client(clientDto)
                               .build()
        );
    }

    @Override
    public String refresh(String refreshToken) {
        return _jwtService.refreshAccess(refreshToken);
    }

    @Override
    public void resendConfirmation(String email) {
        ConfirmationTokenDto dto = _confirmationTokenJpaPort.read(email);

        if (dto.isExpired()) {
            dto.setToken(ConfirmationTokenDto.generateToken());
            dto.setExpiredDate(ConfirmationTokenDto.generateExpiredDate(10));
        }

        _confirmationTokenJpaPort.send(dto);
        _confirmationTokenJpaPort.update(dto);
    }

    @Override
    public void resendRecovery(String email) {
        _recoveryTokenJpaPort.send(_recoveryTokenJpaPort.getByEmail(email));
    }

}