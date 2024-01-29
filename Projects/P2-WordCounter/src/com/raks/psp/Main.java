package com.raks.psp;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    private static final Path FILES_PATH = Path.of(System.getProperty("user.dir"), "files");

    public static void main(String[] args) throws IOException {
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(FILES_PATH)) {
            ds.forEach(filePath -> new WordCounter(filePath).start());
        }
    }

}