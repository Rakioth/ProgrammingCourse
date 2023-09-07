package com.raks.swiftly.infrastructure.mapper;

import com.raks.swiftly.domain.model.dto.PromotionDto;
import com.raks.swiftly.domain.port.EntityMapper;
import com.raks.swiftly.infrastructure.model.entity.Promotion;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {PeriodMapper.class, ProductMapper.class, AuditMapper.class})
public interface PromotionMapper extends EntityMapper<Promotion, PromotionDto> {

    PromotionMapper INSTANCE = Mappers.getMapper(PromotionMapper.class);

}