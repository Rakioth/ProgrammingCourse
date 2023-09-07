package com.raks.psp.example03;

import java.io.*;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        var classPath = Path.of(System.getProperty("user.dir"), "out", "production", "UT1-ProgramacionMultiproceso").toString();
        var process1  = new ProcessBuilder("java", "-cp", classPath, Adder.class.getName(), "1000", "2000").start();
        var process2  = new ProcessBuilder("java", "-cp", classPath, Adder.class.getName(), "2000", "3000").start();

        process1.waitFor();
        process2.waitFor();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(process1.getInputStream()))) {
            System.out.printf("The output of process 1 is: %s%n", br.readLine());
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(process2.getInputStream()))) {
            System.out.printf("The output of process 2 is: %s%n", br.readLine());
        }
    }
}

