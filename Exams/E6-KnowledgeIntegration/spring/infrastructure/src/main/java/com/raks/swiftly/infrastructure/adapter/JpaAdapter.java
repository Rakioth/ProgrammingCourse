package com.raks.swiftly.infrastructure.adapter;

import com.raks.swiftly.domain.exception.EntityNotFoundException;
import com.raks.swiftly.domain.port.PortCrud;
import com.raks.swiftly.domain.port.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@RequiredArgsConstructor
public abstract class JpaAdapter<T, D, ID> implements PortCrud<D, ID> {

    private final JpaRepository<T, ID> _repository;
    private final EntityMapper<T, D>   _mapper;

    @Override
    public D create(D dto) {
        return _mapper.toDto(
                _repository.save(_mapper.toEntity(dto))
        );
    }

    @Override
    public D read(ID id) {
        return _mapper.toDto(
                _repository.findById(id)
                           .orElseThrow(EntityNotFoundException::new)
        );
    }

    @Override
    public void update(D dto) {
        _repository.save(_mapper.toEntity(dto));
    }

    @Override
    public void delete(ID id) {
        _repository.deleteById(id);
    }

    @Override
    public List<D> getAll() {
        return _mapper.toDtoList(_repository.findAll());
    }

}