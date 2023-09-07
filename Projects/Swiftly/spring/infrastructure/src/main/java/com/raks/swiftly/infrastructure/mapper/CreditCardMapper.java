package com.raks.swiftly.infrastructure.mapper;

import com.raks.swiftly.domain.model.dto.CreditCardDto;
import com.raks.swiftly.domain.port.EntityMapper;
import com.raks.swiftly.infrastructure.model.embeddable.CreditCard;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CardTypeMapper.class})
public interface CreditCardMapper extends EntityMapper<CreditCard, CreditCardDto> {

    CreditCardMapper INSTANCE = Mappers.getMapper(CreditCardMapper.class);

}