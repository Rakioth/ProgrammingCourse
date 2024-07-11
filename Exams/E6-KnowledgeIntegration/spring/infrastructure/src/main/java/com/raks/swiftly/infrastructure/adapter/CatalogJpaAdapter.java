package com.raks.swiftly.infrastructure.adapter;

import com.raks.swiftly.domain.exception.EntityNotFoundException;
import com.raks.swiftly.domain.model.dto.CatalogDto;
import com.raks.swiftly.domain.port.spi.CatalogJpaPort;
import com.raks.swiftly.infrastructure.mapper.CatalogMapper;
import com.raks.swiftly.infrastructure.model.entity.Catalog;
import com.raks.swiftly.infrastructure.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogJpaAdapter extends JpaAdapter<Catalog, CatalogDto, Long> implements CatalogJpaPort {

    private final CatalogRepository _catalogRepository;
    private final CatalogMapper     _catalogMapper;

    @Autowired
    public CatalogJpaAdapter(CatalogRepository catalogRepository) {
        super(catalogRepository, CatalogMapper.INSTANCE);
        _catalogRepository = catalogRepository;
        _catalogMapper     = CatalogMapper.INSTANCE;
    }

    @Override
    public CatalogDto getBySupplierId(Long id) {
        return _catalogMapper.toDto(
                _catalogRepository.findBySupplier_Id(id)
                                  .orElseThrow(EntityNotFoundException::new)
        );
    }

}