package com.raks.swiftly.infrastructure.adapter;

import com.raks.swiftly.domain.model.helper.ClientRequestDto;
import com.raks.swiftly.domain.model.dto.ClientDto;
import com.raks.swiftly.domain.port.spi.ClientJpaPort;
import com.raks.swiftly.infrastructure.mapper.ClientMapper;
import com.raks.swiftly.infrastructure.model.entity.Client;
import com.raks.swiftly.infrastructure.model.enums.ClientType;
import com.raks.swiftly.infrastructure.repository.ClientRepository;
import com.raks.swiftly.infrastructure.repository.ClientTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientJpaAdapter extends JpaAdapter<Client, ClientDto, Long> implements ClientJpaPort {

    private final ClientRepository     _clientRepository;
    private final ClientMapper         _clientMapper;
    private final ClientTypeRepository _clientTypeRepository;

    @Autowired
    public ClientJpaAdapter(ClientRepository clientRepository, ClientTypeRepository clientTypeRepository) {
        super(clientRepository, ClientMapper.INSTANCE);
        _clientRepository     = clientRepository;
        _clientMapper         = ClientMapper.INSTANCE;
        _clientTypeRepository = clientTypeRepository;
    }

    @Override
    public ClientDto getByUserId(Long id) {
        return _clientMapper.toDto(
                _clientRepository.findByUser_Id(id)
                                 .orElse(null)
        );
    }

    @Override
    public List<ClientDto> getByParameters(ClientRequestDto request) {
        ClientType clientType = _clientTypeRepository.findById(request.getType())
                                                     .orElse(null);

        return _clientMapper.toDtoList(
                _clientRepository.findClientsByParams(
                        request.getSurname(),
                        clientType,
                        request.getGender(),
                        request.getStartBirthdate(),
                        request.getEndBirthdate(),
                        request.getStartExpenses(),
                        request.getEndExpenses()
                )
        );
    }

}