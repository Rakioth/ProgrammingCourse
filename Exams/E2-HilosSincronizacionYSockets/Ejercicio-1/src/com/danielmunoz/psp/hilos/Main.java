package com.danielmunoz.psp.hilos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.printf("%sAvailable Options%s [-t1 | Exercise Type 1] [-t2 | Exercise Type 2]%n", "\u001B[36m", "\u001B[0m");
        var exit = false;
        var urlsType1 = List.of("https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/io/InputStream.html",
                                "https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/io/OutputStream.html",
                                "https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/io/Flushable.html",
                                "https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/io/Closeable.html");
        var urlsType2 = List.of("https://httpbin.org/image/jpeg",
                                "https://httpbin.org/image/png",
                                "https://httpbin.org/image/svg",
                                "https://httpbin.org/image/webp");

        try (BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
            while (!exit)
                switch (stdIn.readLine()) {
                    case "-t1" -> {
                        var downloaders = urlsType1.stream().map(TextFileDownloader::new).toList();
                        downloaders.forEach(Thread::start);
                        for (TextFileDownloader downloader : downloaders)
                            downloader.join();
                        exit = true;
                    }
                    case "-t2" -> {
                        var downloaders = urlsType2.stream().map(BinaryFileDownloader::new).toList();
                        downloaders.forEach(Thread::start);
                        for (BinaryFileDownloader downloader : downloaders)
                            downloader.join();
                        exit = true;
                    }
                    default    -> System.out.printf("%sUnknown Option%s [-t1 | Exercise Type 1] [-t2 | Exercise Type 2]%n", "\u001B[31m", "\u001B[0m");
                }
        }
    }
}
