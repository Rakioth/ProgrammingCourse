package com.raks.swiftly.infrastructure.adapter;

import com.raks.swiftly.domain.model.dto.WarningDto;
import com.raks.swiftly.domain.port.spi.WarningJpaPort;
import com.raks.swiftly.infrastructure.mapper.WarningMapper;
import com.raks.swiftly.infrastructure.model.entity.Warning;
import com.raks.swiftly.infrastructure.repository.WarningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarningJpaAdapter extends JpaAdapter<Warning, WarningDto, Long> implements WarningJpaPort {

    private final WarningRepository _warningRepository;
    private final WarningMapper     _warningMapper;

    @Autowired
    public WarningJpaAdapter(WarningRepository warningRepository) {
        super(warningRepository, WarningMapper.INSTANCE);
        _warningRepository = warningRepository;
        _warningMapper     = WarningMapper.INSTANCE;
    }

    @Override
    public List<WarningDto> getUnprocessed() {
        return _warningMapper.toDtoList(
                _warningRepository.findByProcessedDateIsNull()
        );
    }

}