package com.raks.swiftly.domain.port.spi;

import com.raks.swiftly.domain.model.dto.TokenDto;
import com.raks.swiftly.domain.model.dto.UserDto;
import com.raks.swiftly.domain.port.PortCrud;

import java.util.List;

public interface UserJpaPort extends PortCrud<UserDto, Long> {

    UserDto getOptional(String username);

    UserDto getByUsername(String username);

    UserDto getByEmail(String email);

    UserDto getByAccessToken(String accessToken);

    List<UserDto> getAdmins();

    TokenDto loginAdmin(String username);

    boolean checkCredentials(UserDto dto);

    boolean checkIfUsernameExists(String username);

    boolean checkIfEmailExists(String email);

}