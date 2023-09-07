package com.raks.swiftly.infrastructure.adapter;

import com.raks.swiftly.domain.model.dto.OrderDto;
import com.raks.swiftly.domain.model.helper.OrderFilterRequestDto;
import com.raks.swiftly.domain.port.spi.OrderJpaPort;
import com.raks.swiftly.infrastructure.mapper.*;
import com.raks.swiftly.infrastructure.model.entity.Order;
import com.raks.swiftly.infrastructure.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderJpaAdapter extends JpaAdapter<Order, OrderDto, Long> implements OrderJpaPort {

    private final OrderRepository _orderRepository;
    private final OrderMapper     _orderMapper;

    @Autowired
    public OrderJpaAdapter(OrderRepository orderRepository) {
        super(orderRepository, OrderMapper.INSTANCE);
        _orderRepository = orderRepository;
        _orderMapper     = OrderMapper.INSTANCE;
    }

    @Override
    public List<OrderDto> getByUserId(Long id) {
        return _orderMapper.toDtoList(
                _orderRepository.findByClient_User_Id(id)
        );
    }

    @Override
    public List<OrderDto> getByParameters(OrderFilterRequestDto request) {
        return _orderMapper.toDtoList(
                _orderRepository.findProductsByParams(
                        request.getUserId(),
                        request.getStartDate(),
                        request.getEndDate(),
                        request.getOrderState(),
                        request.getMinPrice(),
                        request.getMaxPrice()
                )
        );
    }

}