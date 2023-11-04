package com.raks.swiftly.application.api;

import com.raks.swiftly.domain.model.dto.CountryTypeDto;
import com.raks.swiftly.domain.port.spi.CountryTypeJpaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/country-types")
public class CountryTypeController {

    private final CountryTypeJpaPort _countryTypeService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public CountryTypeDto get(@PathVariable("id") String id) {
        return _countryTypeService.read(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String post(@RequestBody CountryTypeDto dto) {
        return _countryTypeService.create(dto).getCode();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void put(@PathVariable("id") String id, @RequestBody CountryTypeDto dto) {
        dto.setCode(id);
        _countryTypeService.update(dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        _countryTypeService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<CountryTypeDto> getAll() {
        return _countryTypeService.getAll();
    }

}