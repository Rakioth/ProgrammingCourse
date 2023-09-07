package com.raks.psp.example01;

import java.io.IOException;

public class StartProcess {
    private static final String ADOBE_ACROBAT = "D:\\Adobe\\Acrobat DC\\Acrobat\\Acrobat.exe";

    public static void main(String[] args) throws IOException, InterruptedException {
        var process = new ProcessBuilder(ADOBE_ACROBAT).start();

        System.out.printf("Process started with PID %d%n", process.pid());
        process.waitFor();
        System.out.printf("Process with PID %d ended with exit value %d%n", process.pid(), process.exitValue());
    }
}


