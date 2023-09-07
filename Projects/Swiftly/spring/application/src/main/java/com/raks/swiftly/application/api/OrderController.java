package com.raks.swiftly.application.api;

import com.raks.swiftly.domain.model.dto.OrderDto;
import com.raks.swiftly.domain.model.dto.UserDto;
import com.raks.swiftly.domain.model.enums.OrderState;
import com.raks.swiftly.domain.model.helper.OrderFilterRequestDto;
import com.raks.swiftly.domain.port.spi.OrderJpaPort;
import com.raks.swiftly.domain.port.spi.UserJpaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderJpaPort _orderService;
    private final UserJpaPort  _userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public OrderDto get(@PathVariable("id") Long id) {
        return _orderService.read(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Long post(@RequestBody OrderDto dto) {
        return _orderService.create(dto).getId();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void put(@PathVariable("id") Long id, @RequestBody OrderState state) {
        OrderDto dto = _orderService.read(id);
        dto.setState(state);
        _orderService.update(dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        _orderService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<OrderDto> getAll() {
        return _orderService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/access")
    public List<OrderDto> get(@CookieValue("access_token") String accessToken) {
        UserDto dto = _userService.getByAccessToken(accessToken);
        return _orderService.getByUserId(dto.getId());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/filter")
    public List<OrderDto> filter(@CookieValue("access_token") String accessToken, @RequestBody OrderFilterRequestDto dto) {
        UserDto userDto = _userService.getByAccessToken(accessToken);
        dto.setUserId(userDto.getId());
        return _orderService.getByParameters(dto);
    }

}