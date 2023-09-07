package com.raks.psp.tests;

import com.raks.psp.example01.AesEncryption;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

class AesEncryptionTests {

    @Test
    void givenString_WhenEncrypt_ThenDecryptSucceeds() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        String          input      = "Programación de Servicios y Procesos";
        SecretKey       secretKey  = AesEncryption.generateKey(128);
        IvParameterSpec iv         = AesEncryption.generateIV();
        String          cipherText = AesEncryption.encrypt("AES/CBC/PKCS5Padding", input, secretKey, iv);
        String          plainText  = AesEncryption.decrypt("AES/CBC/PKCS5Padding", cipherText, secretKey, iv);
        Assertions.assertEquals(input, plainText);
    }

    @Test
    void givenString_WhenEncryptWithPassword_ThenDecryptSucceeds() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        String          input      = "Programación de Servicios y Procesos";
        SecretKey       secretKey  = AesEncryption.fromPassword("Desarrollo Aplicaciones Multiplataforma", "0490", 128);
        IvParameterSpec iv         = AesEncryption.generateIV();
        String          cipherText = AesEncryption.encrypt("AES/CBC/PKCS5Padding", input, secretKey, iv);
        String          plainText  = AesEncryption.decrypt("AES/CBC/PKCS5Padding", cipherText, secretKey, iv);
        Assertions.assertEquals(input, plainText);
    }

}
