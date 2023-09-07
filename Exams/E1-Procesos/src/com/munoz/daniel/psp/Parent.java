package com.munoz.daniel.psp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Parent {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.printf("%sAvailable Options%s [-t1 | Exercise Type 1] [-t2 | Exercise Type 2]%n", "\u001B[36m", "\u001B[0m");
        var exit = false;
        var stringType1 = List.of("Sed eu cursus sem, id vehicula odio.",
                                  "Mauris sagittis non elit sed bibendum.",
                                  "Fusce finibus sollicitudin vestibulum.");
        var stringType2 = List.of("Proin felis turpis, convallis a vehicula a, placerat non sem.",
                                  "Maecenas non ipsum nisl.",
                                  "Ut tincidunt nisl a efficitur faucibus.");

        try (BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
            while (!exit)
                switch (stdIn.readLine()) {
                    case "-t1" -> {
                        getProcess("-t1", stringType1);
                        exit = true;
                    }
                    case "-t2" -> {
                        getProcess("-t2", stringType2);
                        exit = true;
                    }
                    default    -> System.out.printf("%sUnknown Option%s [-t1 | Exercise Type 1] [-t2 | Exercise Type 2]%n", "\u001B[31m", "\u001B[0m");
                }
        }
    }

    private static void getProcess(String option, List<String> stringList) throws IOException, InterruptedException {
        String        classPath = Path.of(System.getProperty("user.dir"), "out", "production", "E1-Procesos").toString();
        String        className = Child.class.getName();
        List<Process> processes = new ArrayList<>();

        for (String str : stringList)
            processes.add(new ProcessBuilder("java", "-cp", classPath, className, option, str).start());

        for (Process process : processes) {
            process.waitFor();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                System.out.println(br.readLine());
            }
        }
        for (int index = 0; index < processes.size(); index++)
             System.out.printf("El proceso %d terminó con estado %d%n", index + 1, processes.get(index).exitValue());
    }
}
