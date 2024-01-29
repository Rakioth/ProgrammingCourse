package com.raks.swiftly.infrastructure.adapter;

import com.raks.swiftly.domain.model.dto.CardTypeDto;
import com.raks.swiftly.domain.port.spi.CardTypeJpaPort;
import com.raks.swiftly.infrastructure.mapper.CardTypeMapper;
import com.raks.swiftly.infrastructure.model.enums.CardType;
import com.raks.swiftly.infrastructure.repository.CardTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardTypeJpaAdapter extends JpaAdapter<CardType, CardTypeDto, String> implements CardTypeJpaPort {

    @Autowired
    public CardTypeJpaAdapter(CardTypeRepository cardTypeRepository) {
        super(cardTypeRepository, CardTypeMapper.INSTANCE);
    }

}