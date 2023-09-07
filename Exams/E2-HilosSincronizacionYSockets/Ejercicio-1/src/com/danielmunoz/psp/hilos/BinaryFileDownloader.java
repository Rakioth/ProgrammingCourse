package com.danielmunoz.psp.hilos;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BinaryFileDownloader extends Thread {
    private static final int NUM_BUFFER = 2048;

    private URL  _url;
    private Path _path;

    public BinaryFileDownloader(String url) {
        try {
            _url  = new URL(url);
            _path = Paths.get(System.getProperty("user.home"), "downloads", String.format("file.%s", Paths.get(_url.getPath()).getFileName().toString()));
        } catch (MalformedURLException e) {
            System.err.println("ERROR: URL Malformed");
        }
    }

    @Override
    public void run() {
        byte[] buffer = new byte[NUM_BUFFER];
        int    index;

        try (
                InputStream is = _url.openStream();
                FileOutputStream fos = new FileOutputStream(_path.toString())
        ) {
            while ((index = is.read(buffer, 0, NUM_BUFFER)) != -1)
                fos.write(buffer, 0, index);
            System.out.println(_path);
        } catch (NullPointerException | IOException e) {
            System.err.println("ERROR: I/O Not Found");
        }
    }
}
