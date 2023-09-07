package com.raks.swiftly.infrastructure.mapper;

import com.raks.swiftly.domain.model.dto.UserDto;
import com.raks.swiftly.domain.port.EntityMapper;
import com.raks.swiftly.infrastructure.model.entity.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends EntityMapper<User, UserDto> {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

}