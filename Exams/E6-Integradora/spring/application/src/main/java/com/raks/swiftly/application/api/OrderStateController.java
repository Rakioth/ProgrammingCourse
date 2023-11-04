package com.raks.swiftly.application.api;

import com.raks.swiftly.domain.model.dto.EnumDto;
import com.raks.swiftly.domain.model.enums.OrderState;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order-states")
public class OrderStateController {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<EnumDto> getAll() {
        return Arrays.stream(OrderState.values())
                     .map(state ->
                                  EnumDto.builder()
                                         .code(state.name())
                                         .ref(state.getRef())
                                         .build()
                     )
                     .toList();
    }

}