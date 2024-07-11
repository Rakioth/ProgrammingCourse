package com.raks.swiftly.infrastructure.adapter;

import com.raks.swiftly.domain.model.dto.SupplierDto;
import com.raks.swiftly.domain.port.spi.SupplierJpaPort;
import com.raks.swiftly.infrastructure.mapper.SupplierMapper;
import com.raks.swiftly.infrastructure.model.entity.Supplier;
import com.raks.swiftly.infrastructure.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierJpaAdapter extends JpaAdapter<Supplier, SupplierDto, Long> implements SupplierJpaPort {

    @Autowired
    public SupplierJpaAdapter(SupplierRepository supplierRepository) {
        super(supplierRepository, SupplierMapper.INSTANCE);
    }

}