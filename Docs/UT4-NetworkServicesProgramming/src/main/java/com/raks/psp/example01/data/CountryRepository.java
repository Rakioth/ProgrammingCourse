package com.raks.psp.example01.data;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.*;

@Repository
public class CountryRepository {

    private final Map<String, Country> countries = new HashMap<>();

    @PostConstruct
    public void initData() {
        Country spain = new Country();
        spain.setCode("es");
        spain.setName("España");
        spain.setCapital("Madrid");
        spain.setCurrency(Currency.getInstance("EUR").getDisplayName());
        spain.setPopulation(47_326_687);
        countries.put(spain.getCode(), spain);

        Country portugal = new Country();
        portugal.setCode("pt");
        portugal.setName("Portugal");
        portugal.setCapital("Lisboa");
        portugal.setCurrency(Currency.getInstance("EUR").getDisplayName());
        portugal.setPopulation(10_298_252);
        countries.put(portugal.getCode(), portugal);

        Country peru = new Country();
        peru.setCode("pe");
        peru.setName("Perú");
        peru.setCapital("Lima");
        peru.setCurrency(Currency.getInstance("PEN").getDisplayName());
        peru.setPopulation(33_738_178);
        countries.put(peru.getCode(), peru);
    }

    public List<Country> findAll() {
        return new ArrayList<>(countries.values());
    }

    public Optional<Country> findByCode(String code) {
        Assert.notNull(code, "El código del país no puede ser nulo");
        return (countries.get(code) != null) ? Optional.of(countries.get(code)) : Optional.empty();
    }

    public Country save(Country country) {
        country.setCurrency(Currency.getInstance(country.getCurrency()).getDisplayName());
        countries.putIfAbsent(country.getCode(), country);
        return country;
    }

}