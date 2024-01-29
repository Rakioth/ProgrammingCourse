package com.raks.swiftly.application.api;

import com.raks.swiftly.domain.model.dto.StoreDto;
import com.raks.swiftly.domain.port.spi.StoreJpaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class StoreController {

    private final StoreJpaPort _storeService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public StoreDto get(@PathVariable("id") Long id) {
        return _storeService.read(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Long post(@RequestBody StoreDto dto) {
        return _storeService.create(dto).getId();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void put(@PathVariable("id") Long id, @RequestBody StoreDto dto) {
        dto.setId(id);
        _storeService.update(dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        _storeService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<StoreDto> getAll() {
        return _storeService.getAll();
    }

}