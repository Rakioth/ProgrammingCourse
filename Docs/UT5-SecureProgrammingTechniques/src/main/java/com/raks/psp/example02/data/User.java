package com.raks.psp.example02.data;

import lombok.Data;

@Data
public class User {

    private final String _username;
    private final String _encryptedPassword;

}