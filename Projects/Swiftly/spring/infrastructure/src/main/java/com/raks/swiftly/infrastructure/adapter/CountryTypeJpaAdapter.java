package com.raks.swiftly.infrastructure.adapter;

import com.raks.swiftly.domain.model.dto.CountryTypeDto;
import com.raks.swiftly.domain.port.spi.CountryTypeJpaPort;
import com.raks.swiftly.infrastructure.mapper.CountryTypeMapper;
import com.raks.swiftly.infrastructure.model.enums.CountryType;
import com.raks.swiftly.infrastructure.repository.CountryTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryTypeJpaAdapter extends JpaAdapter<CountryType, CountryTypeDto, String> implements CountryTypeJpaPort {

    @Autowired
    public CountryTypeJpaAdapter(CountryTypeRepository countryTypeRepository) {
        super(countryTypeRepository, CountryTypeMapper.INSTANCE);
    }

}