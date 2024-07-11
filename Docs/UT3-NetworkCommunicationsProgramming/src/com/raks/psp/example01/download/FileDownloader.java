package com.raks.psp.example01.download;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class FileDownloader {

    protected final URL  _url;
    protected final Path _path;

    public FileDownloader(String url, String first, String... more) throws MalformedURLException {
        Path testPath = Paths.get(first, more);
        if (!testPath.isAbsolute())
            testPath = Paths.get(System.getProperty("user.home"), "downloads", testPath.toString());
        _path = testPath;
        _url  = new URL(url);
    }

    public abstract void download() throws IOException;

}