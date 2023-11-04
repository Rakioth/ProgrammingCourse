package com.raks.psp.example01;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ShaHashing {

    public static String hash(String source) throws NoSuchAlgorithmException {
        MessageDigest digest            = MessageDigest.getInstance("SHA-256");
        byte[]        hashBytes         = digest.digest(source.getBytes(StandardCharsets.UTF_8));
        StringBuilder hashStringBuilder = new StringBuilder();
        for (byte aByte : hashBytes)
            hashStringBuilder.append(String.format("%02x", aByte));
        return hashStringBuilder.toString();
    }

}