package com.raks.swiftly.infrastructure.adapter;

import com.raks.swiftly.domain.exception.EntityNotFoundException;
import com.raks.swiftly.domain.model.dto.ShoppingCartDto;
import com.raks.swiftly.domain.port.spi.ShoppingCartJpaPort;
import com.raks.swiftly.infrastructure.mapper.ShoppingCartMapper;
import com.raks.swiftly.infrastructure.model.entity.ShoppingCart;
import com.raks.swiftly.infrastructure.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartJpaAdapter extends JpaAdapter<ShoppingCart, ShoppingCartDto, Long> implements ShoppingCartJpaPort {

    private final ShoppingCartRepository _shoppingCartRepository;
    private final ShoppingCartMapper     _shoppingCartMapper;

    @Autowired
    public ShoppingCartJpaAdapter(ShoppingCartRepository shoppingCartRepository) {
        super(shoppingCartRepository, ShoppingCartMapper.INSTANCE);
        _shoppingCartRepository = shoppingCartRepository;
        _shoppingCartMapper     = ShoppingCartMapper.INSTANCE;
    }

    @Override
    public ShoppingCartDto getByUserId(Long id) {
        return _shoppingCartMapper.toDto(
                _shoppingCartRepository.getShoppingCartByClient_User_Id(id)
                                       .orElseThrow(EntityNotFoundException::new)
        );
    }

}