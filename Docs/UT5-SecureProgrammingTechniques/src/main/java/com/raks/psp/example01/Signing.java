package com.raks.psp.example01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.util.Base64;

public class Signing {

    public static String sign(Path file, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, IOException, SignatureException {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        byte[] messageBytes = Files.readAllBytes(file);
        signature.update(messageBytes);
        byte[] digitalSignature = signature.sign();
        return Base64.getEncoder().encodeToString(digitalSignature);
    }

    public static boolean verify(Path file, String digitalSignature, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException, IOException, SignatureException {
        byte[]    receivedSignature = Base64.getDecoder().decode(digitalSignature);
        Signature signature         = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        byte[] messageBytes = Files.readAllBytes(file);
        signature.update(messageBytes);
        return signature.verify(receivedSignature);
    }

}