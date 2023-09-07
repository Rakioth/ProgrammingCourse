package com.raks.psp.example02;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        var classPath = Path.of(System.getProperty("user.dir"), "out", "production", "UT1-ProgramacionMultiproceso").toString();
        var process1  = new ProcessBuilder("java", "-cp", classPath, Adder.class.getName(), "proceso 1", "1000", "2000").inheritIO().start();
        var process2  = new ProcessBuilder("java", "-cp", classPath, Adder.class.getName(), "proceso 2", "2000", "3000").inheritIO().start();

        process1.waitFor();
        process2.waitFor();
        System.out.println("Finished both processes");
    }
}
