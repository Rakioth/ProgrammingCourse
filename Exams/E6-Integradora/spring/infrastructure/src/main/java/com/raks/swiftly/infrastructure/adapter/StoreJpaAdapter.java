package com.raks.swiftly.infrastructure.adapter;

import com.raks.swiftly.domain.model.dto.StoreDto;
import com.raks.swiftly.domain.port.spi.StoreJpaPort;
import com.raks.swiftly.infrastructure.mapper.StoreMapper;
import com.raks.swiftly.infrastructure.model.entity.Store;
import com.raks.swiftly.infrastructure.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreJpaAdapter extends JpaAdapter<Store, StoreDto, Long> implements StoreJpaPort {

    @Autowired
    public StoreJpaAdapter(StoreRepository storeRepository) {
        super(storeRepository, StoreMapper.INSTANCE);
    }

}