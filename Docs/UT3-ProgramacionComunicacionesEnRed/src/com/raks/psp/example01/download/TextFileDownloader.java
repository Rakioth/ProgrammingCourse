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
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(_url.openStream()));
                PrintWriter printWriter = new PrintWriter(new FileWriter(_path.toString()))
        ) {
            String line;
            while ((line = bufferedReader.readLine()) != null)
                printWriter.println(line);
        }
    }
}
