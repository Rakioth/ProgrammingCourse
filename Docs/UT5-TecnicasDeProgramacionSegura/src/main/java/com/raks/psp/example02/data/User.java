package com.raks.psp.example02.data;

public class User {
    private final String _username;
    private final String _encryptedPassword;

    public User(String username, String encryptedPassword) {
        _username          = username;
        _encryptedPassword = encryptedPassword;
    }

    public String get_username() {
        return _username;
    }

    public String get_encryptedPassword() {
        return _encryptedPassword;
    }
}
