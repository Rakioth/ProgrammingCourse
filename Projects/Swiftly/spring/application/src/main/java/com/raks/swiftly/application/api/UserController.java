package com.raks.swiftly.application.api;

import com.raks.swiftly.application.util.CookieManager;
import com.raks.swiftly.domain.model.dto.TokenDto;
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
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserJpaPort   _userService;
    private final ClientJpaPort _clientService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public UserDto get(@PathVariable("id") Long id) {
        return _userService.read(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Long post(@RequestBody UserDto dto) {
        return _userService.create(dto).getId();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void put(@PathVariable("id") Long id, @RequestBody UserDto dto) {
        dto.setId(id);
        _userService.update(dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        _userService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<UserDto> getAll() {
        return _userService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/unblock/{id}")
    public void unblock(@PathVariable("id") Long id) {
        UserDto dto = _userService.read(id);
        dto.setReleaseBlock(null);
        _userService.update(dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/destroy/{id}")
    public void destroy(@PathVariable("id") Long id) {
        UserDto dto = _userService.read(id);
        dto.setRole(Role.DELETED);
        _userService.update(dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/recover/{id}")
    public void recover(@PathVariable("id") Long id) {
        UserDto dto = _userService.read(id);
        if (_clientService.getByUserId(id) == null)
            dto.setRole(Role.USER);
        else
            dto.setRole(Role.CLIENT);
        _userService.update(dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/admin/{username}")
    public void admin(@PathVariable("username") String username) {
        TokenDto token = _userService.loginAdmin(username);
        CookieManager.create("access_token", token.getAccessToken());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/is-client")
    public boolean isClient(@CookieValue(name = "access_token", defaultValue = "empty") String accessToken) {
        if (accessToken.equals("empty")) return false;

        UserDto dto = _userService.getByAccessToken(accessToken);
        return dto.getRole() == Role.CLIENT;
    }

}