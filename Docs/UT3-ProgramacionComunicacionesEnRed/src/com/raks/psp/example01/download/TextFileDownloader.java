package com.raks.psp.example01.download;

import java.io.*;
import java.net.MalformedURLException;

public class TextFileDownloader extends FileDownloader {

    public TextFileDownloader(String url, String first, String... more) throws MalformedURLException {
        super(url, first, more);
    }

    @Override
    public void download() throws IOException {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(_url.openStream()));
                PrintWriter    pw = new PrintWriter(new FileWriter(_path.toString()))
        ) {
            String line;
            while ((line = br.readLine()) != null)
                pw.println(line);
        }
    }

}