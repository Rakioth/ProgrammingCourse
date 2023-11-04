package com.raks.psp.tests;

import com.raks.psp.example01.ShaHashing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

class ShaHashingTests {

    @Test
    void givenString_WhenHashingWithSHA256_ThenSuccess() throws NoSuchAlgorithmException {
        String input = "Programación de Servicios y Procesos";
        Assertions.assertEquals(ShaHashing.hash(input), "32e7616c3d9093498cb8884a231a76346d6f836edfa6a8b97ccfede2d805913b");
    }

}