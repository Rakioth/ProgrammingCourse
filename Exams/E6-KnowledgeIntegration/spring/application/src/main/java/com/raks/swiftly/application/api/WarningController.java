package com.raks.swiftly.application.api;

import com.raks.swiftly.domain.model.dto.WarningDto;
import com.raks.swiftly.domain.port.spi.UserJpaPort;
import com.raks.swiftly.domain.port.spi.WarningJpaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/warnings")
public class WarningController {

    private final WarningJpaPort _warningService;
    private final UserJpaPort    _userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public WarningDto get(@PathVariable("id") Long id) {
        return _warningService.read(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Long post(@RequestBody WarningDto dto) {
        return _warningService.create(dto).getId();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void put(@PathVariable("id") Long id, @RequestBody WarningDto dto) {
        dto.setId(id);
        _warningService.update(dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        _warningService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<WarningDto> getAll() {
        return _warningService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/process/{id}")
    public void process(@PathVariable("id") Long id, @CookieValue("access_token") String accessToken) {
        WarningDto dto = _warningService.read(id);
        dto.setProcessedDate(LocalDate.now());
        dto.setProcessedBy(_userService.getByAccessToken(accessToken));
        _warningService.update(dto);
    }

}