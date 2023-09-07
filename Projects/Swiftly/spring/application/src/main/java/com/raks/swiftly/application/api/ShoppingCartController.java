package com.raks.swiftly.application.api;

import com.raks.swiftly.domain.model.dto.*;
import com.raks.swiftly.domain.model.enums.OrderState;
import com.raks.swiftly.domain.port.spi.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shopping-carts")
public class ShoppingCartController {

    private final ShoppingCartJpaPort _shoppingCartService;
    private final ClientJpaPort       _clientService;
    private final OrderJpaPort        _orderService;
    private final ProductJpaPort      _productService;
    private final UserJpaPort         _userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ShoppingCartDto get(@PathVariable("id") Long id) {
        return _shoppingCartService.read(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Long post(@RequestBody ShoppingCartDto dto) {
        return _shoppingCartService.create(dto).getId();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void put(@PathVariable("id") Long id, @RequestBody ShoppingCartDto dto) {
        dto.setId(id);
        _shoppingCartService.update(dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        _shoppingCartService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ShoppingCartDto> getAll() {
        return _shoppingCartService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/access")
    public ShoppingCartDto get(@CookieValue("access_token") String accessToken) {
        UserDto dto = _userService.getByAccessToken(accessToken);
        return _shoppingCartService.getByUserId(dto.getId());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/remove/{id}")
    public void remove(@CookieValue("access_token") String accessToken, @PathVariable("id") Long id) {
        UserDto         userDto = _userService.getByAccessToken(accessToken);
        ShoppingCartDto dto     = _shoppingCartService.getByUserId(userDto.getId());
        dto.getShoppingCartDetails().removeIf(detail -> Objects.equals(detail.getProduct().getId(), id));
        BigDecimal totalPrice = dto.getShoppingCartDetails().stream()
                                   .map(detail -> detail.getProduct().getPrice().multiply(BigDecimal.valueOf(detail.getUnits())))
                                   .reduce(BigDecimal.ZERO, BigDecimal::add);
        dto.setPrice(totalPrice);
        _shoppingCartService.update(dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping("/add/{id}")
    public void add(@CookieValue("access_token") String accessToken, @PathVariable("id") Long id) {
        UserDto         userDto    = _userService.getByAccessToken(accessToken);
        ShoppingCartDto dto        = _shoppingCartService.getByUserId(userDto.getId());
        ProductDto      productDto = _productService.read(id);

        if (dto.getShoppingCartDetails().stream().anyMatch(item -> Objects.equals(item.getProduct().getId(), productDto.getId()))) {
            dto.getShoppingCartDetails().stream()
               .filter(item -> Objects.equals(item.getProduct().getId(), productDto.getId()))
               .findFirst()
               .ifPresent(item -> {
                   if ((item.getProduct().getStock() - item.getProduct().getHiddenThreshold()) >= item.getUnits())
                       item.setUnits(item.getUnits() + 1);
               });
        } else {
            dto.getShoppingCartDetails().add(
                    ShoppingCartDetailDto.builder()
                                         .product(productDto)
                                         .units(1)
                                         .build()
            );
        }

        BigDecimal totalPrice = dto.getShoppingCartDetails().stream()
                                   .map(detail -> detail.getProduct().getPrice().multiply(BigDecimal.valueOf(detail.getUnits())))
                                   .reduce(BigDecimal.ZERO, BigDecimal::add);

        dto.setPrice(totalPrice);
        _shoppingCartService.update(dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/units/{id}/{units}")
    public void units(@CookieValue("access_token") String accessToken, @PathVariable("id") Long id, @PathVariable("units") Integer units) {
        UserDto         userDto = _userService.getByAccessToken(accessToken);
        ShoppingCartDto dto     = _shoppingCartService.getByUserId(userDto.getId());

        dto.getShoppingCartDetails().stream()
           .filter(item -> Objects.equals(item.getProduct().getId(), id))
           .findFirst()
           .ifPresent(item -> {
               if ((item.getProduct().getStock() - item.getProduct().getHiddenThreshold()) >= item.getUnits())
                   item.setUnits(units);
           });

        BigDecimal totalPrice = dto.getShoppingCartDetails().stream()
                                   .map(detail -> detail.getProduct().getPrice().multiply(BigDecimal.valueOf(detail.getUnits())))
                                   .reduce(BigDecimal.ZERO, BigDecimal::add);

        dto.setPrice(totalPrice);
        _shoppingCartService.update(dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/clear")
    public void clear(@CookieValue("access_token") String accessToken) {
        UserDto         userDto = _userService.getByAccessToken(accessToken);
        ShoppingCartDto dto     = _shoppingCartService.getByUserId(userDto.getId());
        dto.setShoppingCartDetails(Collections.emptyList());
        dto.setPrice(BigDecimal.ZERO);
        _shoppingCartService.update(dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/buy")
    public void buy(@CookieValue("access_token") String accessToken) {
        UserDto         userDto = _userService.getByAccessToken(accessToken);
        ShoppingCartDto dto     = _shoppingCartService.getByUserId(userDto.getId());

        if (dto.getShoppingCartDetails().isEmpty()) return;

        BigDecimal priceWithDiscount = BigDecimal.ZERO;

        for (ShoppingCartDetailDto detailDto : dto.getShoppingCartDetails()) {
            ProductDto productDto = detailDto.getProduct();
            productDto.setStock(productDto.getStock() - detailDto.getUnits());
            productDto.setSoldUnits(productDto.getSoldUnits() + detailDto.getUnits());

            BigDecimal cost;

            if (detailDto.getProduct().getOnSale())
                cost = detailDto.getProduct().getPrice().multiply(BigDecimal.valueOf(0.85)).multiply(BigDecimal.valueOf(detailDto.getUnits()));
            else
                cost = detailDto.getProduct().getPrice().multiply(BigDecimal.valueOf(detailDto.getUnits()));

            priceWithDiscount = priceWithDiscount.add(cost);

            productDto.setAccumulatedExpenses(productDto.getAccumulatedExpenses().add(cost));
            _productService.update(productDto);
        }

        ClientDto clientDto = dto.getClient();
        clientDto.setAccumulatedExpenses(clientDto.getAccumulatedExpenses().add(priceWithDiscount));
        _clientService.update(clientDto);

        List<OrderDetailDto> orderDetails = dto.getShoppingCartDetails().
                                               stream()
                                               .map(detail ->
                                                            OrderDetailDto.builder()
                                                                          .product(detail.getProduct())
                                                                          .units(detail.getUnits())
                                                                          .price(detail.getProduct().getPrice())
                                                                          .build()
                                               ).toList();

        OrderDto orderDto = OrderDto.builder()
                                    .date(LocalDateTime.now())
                                    .orderDetails(orderDetails)
                                    .totalPrice(priceWithDiscount)
                                    .client(dto.getClient())
                                    .state(OrderState.BEING_PREPARED)
                                    .processedBy(userDto)
                                    .build();

        dto.setShoppingCartDetails(Collections.emptyList());
        dto.setPrice(BigDecimal.ZERO);
        _shoppingCartService.update(dto);
        _orderService.create(orderDto);
    }

}