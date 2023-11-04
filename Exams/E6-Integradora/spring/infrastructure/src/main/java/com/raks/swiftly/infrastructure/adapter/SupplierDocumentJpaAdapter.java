package com.raks.swiftly.infrastructure.adapter;

import com.raks.swiftly.domain.model.dto.SupplierDocumentDto;
import com.raks.swiftly.domain.port.spi.SupplierDocumentJpaPort;
import com.raks.swiftly.infrastructure.mapper.SupplierDocumentMapper;
import com.raks.swiftly.infrastructure.model.enums.SupplierDocument;
import com.raks.swiftly.infrastructure.repository.SupplierDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierDocumentJpaAdapter extends JpaAdapter<SupplierDocument, SupplierDocumentDto, String> implements SupplierDocumentJpaPort {

    @Autowired
    public SupplierDocumentJpaAdapter(SupplierDocumentRepository supplierDocumentRepository) {
        super(supplierDocumentRepository, SupplierDocumentMapper.INSTANCE);
    }

}