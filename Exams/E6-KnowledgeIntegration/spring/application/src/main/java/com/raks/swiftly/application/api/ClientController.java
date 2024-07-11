package com.raks.swiftly.application.api;

import com.raks.swiftly.domain.model.dto.ClientDto;
import com.raks.swiftly.domain.model.dto.UserDto;
import com.raks.swiftly.domain.model.enums.Role;
import com.raks.swiftly.domain.port.spi.ClientJpaPort;
import com.raks.swiftly.domain.port.spi.UserJpaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientJpaPort _clientService;
    private final UserJpaPort   _userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ClientDto get(@PathVariable("id") Long id) {
        return _clientService.read(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Long post(@RequestBody ClientDto dto) {
        return _clientService.create(dto).getId();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void put(@PathVariable("id") Long id, @RequestBody ClientDto dto) {
        dto.setId(id);
        _clientService.update(dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        _clientService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ClientDto> getAll() {
        return _clientService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/access")
    public ClientDto get(@CookieValue("access_token") String accessToken) {
        UserDto userDto = _userService.getByAccessToken(accessToken);
        return _clientService.getByUserId(userDto.getId());
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/destroy")
    public void destroy(@CookieValue("access_token") String accessToken) {
        UserDto userDto = _userService.getByAccessToken(accessToken);
        userDto.setRole(Role.DELETED);
        _userService.update(userDto);
    }

}