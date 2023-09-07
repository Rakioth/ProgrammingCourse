package com.raks.psp.example01.api;

import com.raks.psp.example01.data.Country;
import com.raks.psp.example01.data.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CountryController {
    private final CountryRepository _countryRepository;

    @Autowired
    CountryController(CountryRepository countryRepository) {
        _countryRepository = countryRepository;
    }

    @GetMapping("/countries")
    public Iterable<Country> getAllCountries() {
        return _countryRepository.findAll();
    }

    @GetMapping("/countries/{code}")
    public ResponseEntity<Country> getCountryByCode(@PathVariable(value = "code") String countryCode) {
        return ResponseEntity.of(_countryRepository.findByCode(countryCode));
    }

    @PostMapping("/countries")
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(_countryRepository.save(country));
    }
}
