package com.raks.psp.example01.encoding;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        var original = "The string ü@foo-bar";
        var encoded  = URLEncoder.encode(original, StandardCharsets.UTF_8);
        var decoded  = URLDecoder.decode(original, StandardCharsets.UTF_8);
        System.out.printf("%s URL encoded is: %s%n", original, encoded);
        System.out.printf("%s URL decoded is: %s%n", encoded, decoded);
    }
}
