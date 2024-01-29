package com.raks.swiftly.infrastructure.adapter;

import com.raks.swiftly.domain.model.dto.ProductDto;
import com.raks.swiftly.domain.model.dto.WarningDto;
import com.raks.swiftly.domain.model.enums.WarningType;
import com.raks.swiftly.domain.model.helper.ProductFilterRequestDto;
import com.raks.swiftly.domain.port.spi.ProductJpaPort;
import com.raks.swiftly.domain.port.spi.WarningJpaPort;
import com.raks.swiftly.infrastructure.mapper.ProductMapper;
import com.raks.swiftly.infrastructure.model.entity.Product;
import com.raks.swiftly.infrastructure.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductJpaAdapter extends JpaAdapter<Product, ProductDto, Long> implements ProductJpaPort {

    private final ProductRepository _productRepository;
    private final ProductMapper     _productMapper;
    private final WarningJpaPort    _warningJpaPort;

    @Autowired
    public ProductJpaAdapter(ProductRepository productRepository, WarningJpaPort warningJpaPort) {
        super(productRepository, ProductMapper.INSTANCE);
        _productRepository = productRepository;
        _productMapper     = ProductMapper.INSTANCE;
        _warningJpaPort    = warningJpaPort;
    }

    @Override
    public void update(ProductDto dto) {
        if (dto.getStock() < dto.getRequestThreshold())
            _warningJpaPort.create(
                    WarningDto.builder()
                              .description(String.format("Request product: %s", dto.getModel()))
                              .type(WarningType.MEDIUM)
                              .build()
            );

        if (dto.getStock() <= dto.getHiddenThreshold())
            _warningJpaPort.create(
                    WarningDto.builder()
                              .description(String.format("Hide product: %s", dto.getModel()))
                              .type(WarningType.HIGH)
                              .build()
            );

        super.update(dto);
    }

    @Override
    public List<ProductDto> getAllAdmin() {
        return _productMapper.toDtoList(
                _productRepository.findAll()
        );
    }

    @Override
    public List<ProductDto> getAll() {
        return _productMapper.toDtoList(
                _productRepository.findAll()
        ).stream().filter(productDto -> productDto.getStock() > productDto.getHiddenThreshold()).toList();
    }

    @Override
    public List<ProductDto> getByParameters(ProductFilterRequestDto request) {
        return _productMapper.toDtoList(
                _productRepository.findProductsByParams(
                        request.getModel(),
                        request.getMinPrice(),
                        request.getMaxPrice(),
                        request.getOnSale(),
                        request.getIsNew(),
                        request.getCat()
                )
        ).stream().filter(productDto -> productDto.getStock() > productDto.getHiddenThreshold()).toList();
    }

}