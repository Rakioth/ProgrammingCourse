package com.raks.psp.example01.download;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

public class BinaryFileDownloader extends FileDownloader {

    private static final int NUM_BUFFER = 2048;

    public BinaryFileDownloader(String url, String first, String... more) throws MalformedURLException {
        super(url, first, more);
    }

    @Override
    public void download() throws IOException {
        byte[] buffer = new byte[NUM_BUFFER];
        int    index;

        try (
                InputStream      is  = _url.openStream();
                FileOutputStream fos = new FileOutputStream(_path.toString())
        ) {
            while ((index = is.read(buffer, 0, NUM_BUFFER)) != -1)
                fos.write(buffer, 0, index);
        }
    }

}