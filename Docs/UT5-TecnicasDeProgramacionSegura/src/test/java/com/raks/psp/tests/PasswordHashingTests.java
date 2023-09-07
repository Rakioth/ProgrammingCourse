package com.raks.psp.tests;

import com.raks.psp.example01.PasswordHashing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

class PasswordHashingTests {

    @Test
    void givenRawPassword_whenEncoded_thenMatchesSuccess() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String rawPassword = "password";
        String hashed      = PasswordHashing.hash(rawPassword);
        Assertions.assertTrue(PasswordHashing.matches(rawPassword, hashed));
    }

    @Test
    void givenRawPassword_whenEncodedTwice_thenHashesAreDifferent() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String rawPassword = "password";
        String hashed1     = PasswordHashing.hash(rawPassword);
        String hashed2     = PasswordHashing.hash(rawPassword);
        Assertions.assertNotEquals(hashed1, hashed2);
    }

}
