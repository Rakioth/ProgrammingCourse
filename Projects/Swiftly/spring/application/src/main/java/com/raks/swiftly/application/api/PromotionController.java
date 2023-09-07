package com.raks.swiftly.application.api;

import com.raks.swiftly.domain.model.dto.PromotionDto;
import com.raks.swiftly.domain.port.spi.PromotionJpaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/promotions")
public class PromotionController {

    private final PromotionJpaPort _promotionService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public PromotionDto get(@PathVariable("id") Long id) {
        return _promotionService.read(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Long post(@RequestBody PromotionDto dto) {
        return _promotionService.create(dto).getId();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void put(@PathVariable("id") Long id, @RequestBody PromotionDto dto) {
        dto.setId(id);
        _promotionService.update(dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        _promotionService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<PromotionDto> getAll() {
        return _promotionService.getAll();
    }

}