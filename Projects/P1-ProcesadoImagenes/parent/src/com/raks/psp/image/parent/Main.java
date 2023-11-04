package com.raks.psp.image.parent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String JAR_PATH           = Path.of(System.getProperty("user.dir"), "out", "artifacts", "child_jar", "child.jar").toString();
    private static final Path   IMAGES_PATH        = Path.of(System.getProperty("user.dir"), "lorem");
    private static final int    PROCESSES_QUANTITY = 4;

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.printf("%sAvailable Options%s [-nm | Normal Mode] [-ef | Efficient Mode]%n", "\u001B[36m", "\u001B[0m");
        boolean exit = false;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (!exit)
                switch (br.readLine()) {
                    case "-nm" -> {
                        normalMode();
                        exit = true;
                    }
                    case "-ef" -> {
                        efficientMode();
                        exit = true;
                    }
                    default    -> System.out.printf("%sUnknown Option%s [-nm | Normal Mode] [-ef | Efficient Mode]%n", "\u001B[31m", "\u001B[0m");
                }
        }
    }

    private static void normalMode() throws IOException, InterruptedException {
        List<Process> processes = new ArrayList<>();

        try (DirectoryStream<Path> ds = Files.newDirectoryStream(IMAGES_PATH, "*.jpg")) {
            for (Path imageFile : ds)
                processes.add(new ProcessBuilder("java", "-jar", JAR_PATH, "-nm", imageFile.toString()).start());
        }

        for (Process process : processes) {
            process.waitFor();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                System.out.println(br.readLine());
            }
        }
    }

    private static void efficientMode() throws IOException, InterruptedException {
        List<Process>        processes = getProcesses();
        List<PrintWriter>    writers   = getWriters(processes);
        List<BufferedReader> readers   = getReaders(processes);

        try (DirectoryStream<Path> ds = Files.newDirectoryStream(IMAGES_PATH, "*.jpg")) {
            int index = 0;
            for (Path imageFile : ds) {
                writers.get(index).println(imageFile);
                index++;
                if (index == processes.size()) {
                    readOutput(readers, index);
                    index = 0;
                }
            }
            readOutput(readers, index);
        }

        for (int index = 0; index < processes.size(); index++) {
            writers.get(index).close();
            readers.get(index).close();
            processes.get(index).destroy();
            processes.get(index).waitFor();
        }
    }

    private static List<Process> getProcesses() throws IOException {
        List<Process> processes = new ArrayList<>();
        for (int i = 0; i < PROCESSES_QUANTITY; i++)
             processes.add(new ProcessBuilder("java", "-jar", JAR_PATH, "-ef").start());
        return processes;
    }

    private static List<PrintWriter> getWriters(List<Process> processes) {
        List<PrintWriter> writers = new ArrayList<>();
        processes.forEach(process -> writers.add(new PrintWriter(process.getOutputStream(), true)));
        return writers;
    }

    private static List<BufferedReader> getReaders(List<Process> processes) {
        List<BufferedReader> readers = new ArrayList<>();
        processes.forEach(process -> readers.add(new BufferedReader(new InputStreamReader(process.getInputStream()))));
        return readers;
    }

    private static void readOutput(List<BufferedReader> readers, int index) throws IOException {
        for (int i = 0; i < index; i++)
             System.out.println(readers.get(i).readLine());
    }

}