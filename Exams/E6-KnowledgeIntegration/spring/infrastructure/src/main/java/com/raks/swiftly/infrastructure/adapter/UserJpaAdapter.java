package com.raks.swiftly.infrastructure.adapter;

import com.raks.swiftly.domain.exception.EntityNotFoundException;
import com.raks.swiftly.domain.model.dto.TokenDto;
import com.raks.swiftly.domain.model.enums.Role;
import com.raks.swiftly.domain.port.spi.UserJpaPort;
import com.raks.swiftly.domain.model.dto.UserDto;
import com.raks.swiftly.infrastructure.configuration.jwt.JwtService;
import com.raks.swiftly.infrastructure.mapper.UserMapper;
import com.raks.swiftly.infrastructure.model.entity.User;
import com.raks.swiftly.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserJpaAdapter extends JpaAdapter<User, UserDto, Long> implements UserJpaPort {

    private final UserRepository        _userRepository;
    private final UserMapper            _userMapper;
    private final AuthenticationManager _authenticationManager;
    private final JwtService            _jwtService;

    @Autowired
    public UserJpaAdapter(UserRepository userRepository, AuthenticationManager authenticationManager, JwtService jwtService) {
        super(userRepository, UserMapper.INSTANCE);
        _userRepository        = userRepository;
        _userMapper            = UserMapper.INSTANCE;
        _authenticationManager = authenticationManager;
        _jwtService            = jwtService;
    }

    @Override
    public UserDto getOptional(String username) {
        return _userMapper.toDto(
                _userRepository.findByUsername(username)
                               .orElse(null)
        );
    }

    @Override
    public UserDto getByUsername(String username) {
        return _userMapper.toDto(
                _userRepository.findByUsername(username)
                               .orElseThrow(EntityNotFoundException::new)
        );
    }

    @Override
    public UserDto getByEmail(String email) {
        return _userMapper.toDto(
                _userRepository.findByEmail(email)
                               .orElseThrow(EntityNotFoundException::new)
        );
    }

    @Override
    public UserDto getByAccessToken(String accessToken) {
        return _userMapper.toDto(
                _userRepository.findByUsername(_jwtService.getUsername(accessToken))
                               .orElseThrow(EntityNotFoundException::new)
        );
    }

    @Override
    public List<UserDto> getAdmins() {
        return _userMapper.toDtoList(
                _userRepository.findByRole(Role.ADMIN)
        );
    }

    @Override
    public TokenDto loginAdmin(String username) {
        return _jwtService.createJwt(username);
    }

    @Override
    public boolean checkCredentials(UserDto dto) {
        try {
            _authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.getUsername(),
                            dto.getPassword()
                    )
            );
            return true;
        } catch (BadCredentialsException e) {
            UserDto userDto = getByUsername(dto.getUsername());
            if (userDto.getFailedAuth() < 2) {
                userDto.setFailedAuth(userDto.getFailedAuth() + 1);
                update(userDto);
            } else if (userDto.getReleaseBlock() == null) {
                userDto.setReleaseBlock(LocalDateTime.now().plusMinutes(5));
                update(userDto);
            }
            return false;
        }
    }

    @Override
    public boolean checkIfUsernameExists(String username) {
        return _userRepository.existsByUsername(username);
    }

    @Override
    public boolean checkIfEmailExists(String email) {
        return _userRepository.existsByEmail(email);
    }

}