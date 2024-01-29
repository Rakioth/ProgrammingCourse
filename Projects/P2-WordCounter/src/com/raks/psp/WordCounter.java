package com.raks.psp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class WordCounter extends Thread {

    private final Path _filePath;
    private       int  _wordCounter;

    public WordCounter(Path filePath) {
        _filePath    = filePath;
        _wordCounter = 0;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(_filePath.toString()))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null)
                _wordCounter += currentLine.split(" ").length;
            System.out.printf("File %s has %d words%n", _filePath.getFileName().toString(), _wordCounter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}