package com.raks.psp.example04;

import java.io.*;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        String  classPath = Path.of(System.getProperty("user.dir"), "out", "production", "UT1-ProgramacionMultiproceso").toString();
        Process process1  = new ProcessBuilder("java", "-cp", classPath, Adder.class.getName()).start();
        Process process2  = new ProcessBuilder("java", "-cp", classPath, Adder.class.getName()).start();

        try (
                PrintWriter    pw1 = new PrintWriter(new OutputStreamWriter(process1.getOutputStream()), true);
                BufferedReader br1 = new BufferedReader(new InputStreamReader(process1.getInputStream()));
                PrintWriter    pw2 = new PrintWriter(new OutputStreamWriter(process2.getOutputStream()), true);
                BufferedReader br2 = new BufferedReader(new InputStreamReader(process2.getInputStream()))
        ) {
            pw1.println("1000");
            pw1.println("2000");
            pw2.println("3000");
            pw2.println("4000");

            System.out.printf("The output of process 1 is: %s%n", br1.readLine());
            System.out.printf("The output of process 2 is: %s%n", br2.readLine());

            pw1.println("5000");
            pw1.println("6000");
            pw2.println("6000");
            pw2.println("7000");

            System.out.printf("The output of process 1 is: %s%n", br1.readLine());
            System.out.printf("The output of process 2 is: %s%n", br2.readLine());
        }

        process1.destroy();
        process2.destroy();

        process1.waitFor();
        process2.waitFor();
    }

}