package com.raks.psp.threads;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextFileDownloader extends Thread {

    private URL  _url;
    private Path _path;

    public TextFileDownloader(String url) {
        try {
            _url  = new URL(url);
            _path = Paths.get(System.getProperty("user.home"), "downloads", Paths.get(_url.getPath()).getFileName().toString());
        } catch (MalformedURLException e) {
            System.err.println("ERROR: URL Malformed");
        }
    }

    @Override
    public void run() {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(_url.openStream()));
                PrintWriter    pw = new PrintWriter(new FileWriter(_path.toString()))
        ) {
            String line;
            while ((line = br.readLine()) != null)
                pw.println(line);
            System.out.println(_path);
        } catch (NullPointerException | IOException e) {
            System.err.println("ERROR: I/O Not Found");
        }
    }

}