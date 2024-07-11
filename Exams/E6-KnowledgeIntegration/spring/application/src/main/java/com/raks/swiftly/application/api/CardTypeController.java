package com.raks.swiftly.application.api;

import com.raks.swiftly.domain.model.dto.CardTypeDto;
import com.raks.swiftly.domain.port.spi.CardTypeJpaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/card-types")
public class CardTypeController {

    private final CardTypeJpaPort _cardTypeService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public CardTypeDto get(@PathVariable("id") String id) {
        return _cardTypeService.read(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String post(@RequestBody CardTypeDto dto) {
        return _cardTypeService.create(dto).getCode();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void put(@PathVariable("id") String id, @RequestBody CardTypeDto dto) {
        dto.setCode(id);
        _cardTypeService.update(dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        _cardTypeService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<CardTypeDto> getAll() {
        return _cardTypeService.getAll();
    }

}