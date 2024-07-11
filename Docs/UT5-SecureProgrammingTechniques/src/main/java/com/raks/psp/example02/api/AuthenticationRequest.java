package com.raks.psp.example02.api;

import lombok.Data;

@Data
public class AuthenticationRequest {

    String username;
    String password;

}