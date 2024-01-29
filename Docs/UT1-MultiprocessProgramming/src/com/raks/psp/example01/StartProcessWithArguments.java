package com.raks.psp.example01;

import java.io.IOException;
import java.nio.file.Path;

public class StartProcessWithArguments {

    private static final String ADOBE_ACROBAT = Path.of("C:", "Program Files", "Adobe", "Acrobat DC", "Acrobat", "Acrobat.exe").toString();
    private static final String FILE_PATH     = Path.of(System.getProperty("user.dir"), "src", "com", "raks", "psp", "example01", "ProcessBuilder (Java SE 17 & JDK 17).pdf").toString();

    public static void main(String[] args) throws IOException, InterruptedException {
        Process process = new ProcessBuilder(ADOBE_ACROBAT, FILE_PATH).start();
        System.out.printf("Process started with PID %d%n", process.pid());
        process.waitFor();
        System.out.printf("Process with PID %d ended with exit value %d%n", process.pid(), process.exitValue());
    }

}