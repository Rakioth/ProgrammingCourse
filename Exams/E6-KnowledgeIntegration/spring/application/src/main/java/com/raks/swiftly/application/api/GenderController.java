package com.raks.swiftly.application.api;

import com.raks.swiftly.domain.model.dto.EnumDto;
import com.raks.swiftly.domain.model.enums.Gender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/genders")
public class GenderController {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<EnumDto> getAll() {
        return Arrays.stream(Gender.values())
                     .map(gender ->
                                  EnumDto.builder()
                                         .code(gender.name())
                                         .ref(gender.getRef())
                                         .build()
                     )
                     .toList();
    }

}