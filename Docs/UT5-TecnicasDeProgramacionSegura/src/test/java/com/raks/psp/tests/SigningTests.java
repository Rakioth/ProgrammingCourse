package com.raks.psp.tests;

import com.raks.psp.example01.RsaEncryption;
import com.raks.psp.example01.Signing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

class SigningTests {

    @Test
    void givenFile_whenSigningTwice_thenSignaturesMatch() throws URISyntaxException, NoSuchAlgorithmException, IOException, SignatureException, InvalidKeyException {
        String  resourceName = "The Dunwhich horror by H.P Lovecraft";
        Path    inputFile    = Paths.get(getClass().getClassLoader().getResource(resourceName).toURI());
        KeyPair keyPair      = RsaEncryption.generateKeyPair(2048);
        String  signature1   = Signing.sign(inputFile, keyPair.getPrivate());
        String  signature2   = Signing.sign(inputFile, keyPair.getPrivate());
        Assertions.assertEquals(signature1, signature2);
    }

    @Test
    void givenFile_whenSignedWithPrivateKey_thenVerifiedWithPublicKeySuccess() throws URISyntaxException, NoSuchAlgorithmException, IOException, SignatureException, InvalidKeyException {
        String  resourceName = "The Dunwhich horror by H.P Lovecraft";
        Path    inputFile    = Paths.get(getClass().getClassLoader().getResource(resourceName).toURI());
        KeyPair keyPair      = RsaEncryption.generateKeyPair(2048);
        String  signature    = Signing.sign(inputFile, keyPair.getPrivate());
        Assertions.assertTrue(Signing.verify(inputFile, signature, keyPair.getPublic()));
    }

    @Test
    void givenFile_whenSignedWithPrivateKey_thenVerifiedWithUnrelatedPublicKeyFails() throws URISyntaxException, NoSuchAlgorithmException, IOException, SignatureException, InvalidKeyException {
        String  resourceName = "The Dunwhich horror by H.P Lovecraft";
        Path    inputFile    = Paths.get(getClass().getClassLoader().getResource(resourceName).toURI());
        KeyPair keyPair      = RsaEncryption.generateKeyPair(2048);
        String  signature    = Signing.sign(inputFile, keyPair.getPrivate());
        Assertions.assertFalse(Signing.verify(inputFile, signature, RsaEncryption.generateKeyPair(2048).getPublic()));
    }

    @Test
    void givenFile_whenTampered_thenVerifiedFail() throws URISyntaxException, NoSuchAlgorithmException, IOException, SignatureException, InvalidKeyException {
        String  resourceName = "The Dunwhich horror by H.P Lovecraft";
        Path    inputFile    = Paths.get(getClass().getClassLoader().getResource(resourceName).toURI());
        KeyPair keyPair      = RsaEncryption.generateKeyPair(2048);
        String  signature    = Signing.sign(inputFile, keyPair.getPrivate());
        byte[]  bytes        = Files.readAllBytes(inputFile);
        bytes[0] += 1;
        Path tamperedFile = Paths.get(".", "tmp.txt");
        Files.write(tamperedFile, bytes);
        Assertions.assertFalse(Signing.verify(tamperedFile, signature, keyPair.getPublic()));
        Files.delete(tamperedFile);
    }

}
