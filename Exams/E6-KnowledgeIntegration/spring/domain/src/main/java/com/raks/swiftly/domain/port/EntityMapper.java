package com.raks.swiftly.domain.port;

import java.util.List;

public interface EntityMapper<T, D> {

    T toEntity(D dto);

    D toDto(T entity);

    List<T> toEntityList(List<D> dtos);

    List<D> toDtoList(List<T> entities);

}