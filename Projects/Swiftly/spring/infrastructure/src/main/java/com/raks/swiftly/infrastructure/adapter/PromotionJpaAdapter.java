package com.raks.swiftly.infrastructure.adapter;

import com.raks.swiftly.domain.exception.EntityNotFoundException;
import com.raks.swiftly.domain.model.dto.PromotionDto;
import com.raks.swiftly.domain.port.spi.PromotionJpaPort;
import com.raks.swiftly.infrastructure.mapper.PromotionMapper;
import com.raks.swiftly.infrastructure.model.entity.Promotion;
import com.raks.swiftly.infrastructure.repository.ProductRepository;
import com.raks.swiftly.infrastructure.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionJpaAdapter extends JpaAdapter<Promotion, PromotionDto, Long> implements PromotionJpaPort {

    private final PromotionRepository _promotionRepository;
    private final ProductRepository   _productRepository;

    @Autowired
    public PromotionJpaAdapter(PromotionRepository promotionRepository, ProductRepository productRepository) {
        super(promotionRepository, PromotionMapper.INSTANCE);
        _promotionRepository = promotionRepository;
        _productRepository   = productRepository;
    }

    @Override
    public void delete(Long id) {
        _productRepository.saveAll(
                _promotionRepository.findById(id)
                                    .orElseThrow(EntityNotFoundException::new)
                                    .getProducts()
                                    .stream().peek(product -> product.setPromotion(null)).toList()
        );
        super.delete(id);
    }

}