package com.raks.psp.tests;

import com.raks.psp.example01.AesEncryption;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AesFileEncryptionTests {

    private final Path inputFile;
    private final Path encryptedFile;
    private final Path decryptedFile;

    public AesFileEncryptionTests() throws IOException, URISyntaxException {
        String resourceName = "The Dunwhich horror by H.P Lovecraft";
        inputFile     = Paths.get(getClass().getClassLoader().getResource(resourceName).toURI());
        encryptedFile = Files.createTempFile(resourceName, ".encrypted");
        decryptedFile = Files.createTempFile(resourceName, ".decrypted");
    }

    @AfterEach
    void cleanFiles() throws IOException {
        if (Files.exists(encryptedFile))
            Files.delete(encryptedFile);
        if (Files.exists(decryptedFile))
            Files.delete(decryptedFile);
    }

    @Test
    void givenFile_whenEncrypt_thenSuccess() throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException, IOException {
        SecretKey       key             = AesEncryption.generateKey(128);
        String          algorithm       = "AES/CBC/PKCS5Padding";
        IvParameterSpec ivParameterSpec = AesEncryption.generateIV();
        AesEncryption.encryptFile(algorithm, key, ivParameterSpec, inputFile, encryptedFile);
        AesEncryption.decryptFile(algorithm, key, ivParameterSpec, encryptedFile, decryptedFile);
        Assertions.assertEquals(Files.mismatch(inputFile, decryptedFile), -1L);
    }

}