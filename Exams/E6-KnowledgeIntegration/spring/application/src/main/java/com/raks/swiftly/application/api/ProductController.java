package com.raks.swiftly.application.api;

import com.raks.swiftly.domain.model.dto.ProductDto;
import com.raks.swiftly.domain.model.helper.ProductFilterRequestDto;
import com.raks.swiftly.domain.port.spi.ProductJpaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductJpaPort _productService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ProductDto get(@PathVariable("id") Long id) {
        return _productService.read(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Long post(@RequestBody ProductDto dto) {
        return _productService.create(dto).getId();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void put(@PathVariable("id") Long id, @RequestBody ProductDto dto) {
        dto.setId(id);
        _productService.update(dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        _productService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ProductDto> getAll() {
        return _productService.getAll();
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/filter")
    public List<ProductDto> filter(@RequestBody ProductFilterRequestDto dto) {
        return _productService.getByParameters(dto);
    }

}