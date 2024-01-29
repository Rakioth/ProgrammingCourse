package com.raks.swiftly.infrastructure.adapter;

import com.raks.swiftly.domain.model.dto.SupplierTypeDto;
import com.raks.swiftly.domain.port.spi.SupplierTypeJpaPort;
import com.raks.swiftly.infrastructure.mapper.SupplierTypeMapper;
import com.raks.swiftly.infrastructure.model.enums.SupplierType;
import com.raks.swiftly.infrastructure.repository.SupplierTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierTypeJpaAdapter extends JpaAdapter<SupplierType, SupplierTypeDto, String> implements SupplierTypeJpaPort {

    @Autowired
    public SupplierTypeJpaAdapter(SupplierTypeRepository supplierTypeRepository) {
        super(supplierTypeRepository, SupplierTypeMapper.INSTANCE);
    }

}