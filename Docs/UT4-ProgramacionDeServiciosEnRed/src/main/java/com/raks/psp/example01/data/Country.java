package com.raks.psp.example01.data;

import lombok.Data;

@Data
public class Country {
    private String code;
    private String name;
    private String currency;
    private String capital;
    private int    population;
}
