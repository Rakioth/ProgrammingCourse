package com.raks.swiftly.infrastructure.adapter;

import com.raks.swiftly.domain.model.dto.ClientTypeDto;
import com.raks.swiftly.domain.port.spi.ClientTypeJpaPort;
import com.raks.swiftly.infrastructure.mapper.ClientTypeMapper;
import com.raks.swiftly.infrastructure.model.enums.ClientType;
import com.raks.swiftly.infrastructure.repository.ClientTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientTypeJpaAdapter extends JpaAdapter<ClientType, ClientTypeDto, String> implements ClientTypeJpaPort {

    @Autowired
    public ClientTypeJpaAdapter(ClientTypeRepository clientTypeRepository) {
        super(clientTypeRepository, ClientTypeMapper.INSTANCE);
    }

}