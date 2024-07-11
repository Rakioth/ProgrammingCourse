package com.raks.swiftly.application.api;

import com.raks.swiftly.domain.model.dto.ClientDocumentDto;
import com.raks.swiftly.domain.port.spi.ClientDocumentJpaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/client-documents")
public class ClientDocumentController {

    private final ClientDocumentJpaPort _clientDocumentService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ClientDocumentDto get(@PathVariable("id") String id) {
        return _clientDocumentService.read(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String post(@RequestBody ClientDocumentDto dto) {
        return _clientDocumentService.create(dto).getCode();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void put(@PathVariable("id") String id, @RequestBody ClientDocumentDto dto) {
        dto.setCode(id);
        _clientDocumentService.update(dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        _clientDocumentService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ClientDocumentDto> getAll() {
        return _clientDocumentService.getAll();
    }

}