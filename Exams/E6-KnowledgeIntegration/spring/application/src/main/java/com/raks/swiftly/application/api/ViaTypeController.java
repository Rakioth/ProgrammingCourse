package com.raks.swiftly.application.api;

import com.raks.swiftly.domain.model.dto.ViaTypeDto;
import com.raks.swiftly.domain.port.spi.ViaTypeJpaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/via-types")
public class ViaTypeController {

    private final ViaTypeJpaPort _viaTypeService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ViaTypeDto get(@PathVariable("id") String id) {
        return _viaTypeService.read(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String post(@RequestBody ViaTypeDto dto) {
        return _viaTypeService.create(dto).getCode();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void put(@PathVariable("id") String id, @RequestBody ViaTypeDto dto) {
        dto.setCode(id);
        _viaTypeService.update(dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        _viaTypeService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ViaTypeDto> getAll() {
        return _viaTypeService.getAll();
    }

}