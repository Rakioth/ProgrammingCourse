package com.raks.swiftly.infrastructure.adapter;

import com.raks.swiftly.domain.model.dto.ViaTypeDto;
import com.raks.swiftly.domain.port.spi.ViaTypeJpaPort;
import com.raks.swiftly.infrastructure.mapper.ViaTypeMapper;
import com.raks.swiftly.infrastructure.model.enums.ViaType;
import com.raks.swiftly.infrastructure.repository.ViaTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViaTypeJpaAdapter extends JpaAdapter<ViaType, ViaTypeDto, String> implements ViaTypeJpaPort {

    @Autowired
    public ViaTypeJpaAdapter(ViaTypeRepository viaTypeRepository) {
        super(viaTypeRepository, ViaTypeMapper.INSTANCE);
    }

}