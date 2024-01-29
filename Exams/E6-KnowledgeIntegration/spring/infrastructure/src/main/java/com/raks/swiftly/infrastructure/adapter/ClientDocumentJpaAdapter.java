package com.raks.swiftly.infrastructure.adapter;

import com.raks.swiftly.domain.model.dto.ClientDocumentDto;
import com.raks.swiftly.domain.port.spi.ClientDocumentJpaPort;
import com.raks.swiftly.infrastructure.mapper.ClientDocumentMapper;
import com.raks.swiftly.infrastructure.model.enums.ClientDocument;
import com.raks.swiftly.infrastructure.repository.ClientDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientDocumentJpaAdapter extends JpaAdapter<ClientDocument, ClientDocumentDto, String> implements ClientDocumentJpaPort {

    @Autowired
    public ClientDocumentJpaAdapter(ClientDocumentRepository clientDocumentRepository) {
        super(clientDocumentRepository, ClientDocumentMapper.INSTANCE);
    }

}