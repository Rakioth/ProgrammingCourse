package com.raks.psp.ftp;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {

    private static final FTPClient CLIENT     = new FTPClient();
    private static final String    USERNAME   = "raks";
    private static final String    PASSWORD   = "raksonme";
    private static final Path      FILES_PATH = Path.of(System.getProperty("user.dir"), "files");

    public static void main(String[] args) throws IOException {
        CLIENT.connect(InetAddress.getLocalHost());

        int replyCode = CLIENT.getReplyCode();

        if (!FTPReply.isPositiveCompletion(replyCode)) {
            CLIENT.disconnect();
            System.err.println("ERROR: Unable to establish connection");
            System.exit(replyCode);
        }

        CLIENT.enterLocalPassiveMode();
        CLIENT.login(USERNAME, PASSWORD);
        System.out.printf("Current remote folder is %s%n", CLIENT.printWorkingDirectory());

        for (FTPFile folder : CLIENT.listDirectories()) {
            System.out.printf("About to delete folder: %s%n", folder.getName());
            RemoveRecursively(folder.getName());
        }

        String newFolder = DateTimeFormatter.ofPattern("YYYYMMddHHmmss").format(LocalDateTime.now());
        System.out.printf("Creating remote folder: %s%n", newFolder);
        CLIENT.makeDirectory(newFolder);

        for (FTPFile file : CLIENT.listFiles())
            if (CLIENT.rename(file.getName(), String.join("/", newFolder, file.getName())))
                System.out.printf("Moving remote file %s to remote folder %s%n", file, newFolder);

        CLIENT.changeWorkingDirectory(newFolder);
        System.out.printf("Current remote folder is %s%n", CLIENT.printWorkingDirectory());
        ListingFiles();
        CLIENT.changeToParentDirectory();
        System.out.printf("Current remote folder is %s%n", CLIENT.printWorkingDirectory());

        try (DirectoryStream<Path> ds = Files.newDirectoryStream(FILES_PATH)) {
            for (Path file : ds)
                try (InputStream is = new FileInputStream(file.toString())) {
                    CLIENT.setFileType(FTP.BINARY_FILE_TYPE);
                    System.out.printf("Uploading local file %s%n", file.getFileName());
                    CLIENT.storeFile(file.getFileName().toString(), is);
                }
        }

        System.out.printf("Listing files on remote folder %s%n", CLIENT.printWorkingDirectory());
        ListingFiles();
        CLIENT.disconnect();
    }

    private static void RemoveRecursively(String folder) throws IOException {
        CLIENT.changeWorkingDirectory(folder);

        for (FTPFile file : CLIENT.listFiles())
            if (file.isFile()) {
                System.out.printf("Deleting remote file: %s%n", file.getName());
                CLIENT.deleteFile(file.getName());
            } else RemoveRecursively(file.getName());

        CLIENT.changeToParentDirectory();
        System.out.printf("Deleting remote folder: %s%n", folder);
        CLIENT.removeDirectory(folder);
    }

    private static void ListingFiles() throws IOException {
        FTPFile[] files = CLIENT.listFiles();
        if (files.length == 0) System.out.println("No files");
        List.of(files).forEach(System.out::println);
    }

}