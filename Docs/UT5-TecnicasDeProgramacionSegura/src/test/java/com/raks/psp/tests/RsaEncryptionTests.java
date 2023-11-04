package com.raks.psp.tests;

import com.raks.psp.example01.RsaEncryption;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class RsaEncryptionTests {

    @Test
    public void givenString_WhenEncryptedWithPublicKey_ThenDecryptedWithPrivateKeySuccess() throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        String  input      = "Programación de Servicios y Procesos";
        KeyPair keyPair    = RsaEncryption.generateKeyPair(2048);
        String  cypherText = RsaEncryption.encrypt(input, keyPair.getPublic());
        String  plainText  = RsaEncryption.decrypt(cypherText, keyPair.getPrivate());
        Assertions.assertEquals(input, plainText);
    }

    @Test
    public void givenString_WhenEncryptedWithPublicKey_ThenDecryptedWithPublicKeyFail() throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        String  input      = "Programación de Servicios y Procesos";
        KeyPair keyPair    = RsaEncryption.generateKeyPair(2048);
        String  cypherText = RsaEncryption.encrypt(input, keyPair.getPublic());
        Assertions.assertThrowsExactly(BadPaddingException.class, () -> RsaEncryption.decrypt(cypherText, keyPair.getPublic()));
    }

    @Test
    public void givenString_WhenEncryptedWithPrivateKey_ThenDecryptedWithPublicKeySuccess() throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        String  input      = "Programación de Servicios y Procesos";
        KeyPair keyPair    = RsaEncryption.generateKeyPair(2048);
        String  cypherText = RsaEncryption.encrypt(input, keyPair.getPrivate());
        String  plainText  = RsaEncryption.decrypt(cypherText, keyPair.getPublic());
        Assertions.assertEquals(input, plainText);
    }

    @Test
    public void givenString_WhenEncryptedWithPrivateKey_ThenDecryptedWithPrivateKeyFails() throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        String  input      = "Programación de Servicios y Procesos";
        KeyPair keyPair    = RsaEncryption.generateKeyPair(2048);
        String  cypherText = RsaEncryption.encrypt(input, keyPair.getPrivate());
        Assertions.assertThrowsExactly(BadPaddingException.class, () -> RsaEncryption.decrypt(cypherText, keyPair.getPrivate()));
    }

    @Test
    public void givenString_WhenEncryptWithKey_ThenDecryptedWithSavedKeySuccess() throws NoSuchAlgorithmException, IOException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        String  input          = "Programación de Servicios y Procesos";
        Path    publicKeyPath  = Path.of(".", "id_rsa.pub");
        Path    privateKeyPath = Path.of(".", "id_rsa");
        KeyPair keyPair        = RsaEncryption.writeKeyPair(publicKeyPath, privateKeyPath, 2048);
        String  cypherText     = RsaEncryption.encrypt(input, keyPair.getPrivate());
        keyPair = RsaEncryption.readKeyPair(publicKeyPath, privateKeyPath);
        String plainText = RsaEncryption.decrypt(cypherText, keyPair.getPublic());
        Assertions.assertEquals(input, plainText);
        Files.delete(publicKeyPath);
        Files.delete(privateKeyPath);
    }

}