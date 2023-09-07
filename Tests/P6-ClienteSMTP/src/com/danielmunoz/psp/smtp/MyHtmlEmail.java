package com.danielmunoz.psp.smtp;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

public class MyHtmlEmail {
    private static final int    PORT      = 465;
    private static final String HOSTNAME  = "smtp.educa.madrid.org";
    private static final String RECIPIENT = "juan.agui2@educa.madrid.org";
    private static final Path   HTML_PATH = Path.of(System.getProperty("user.dir"), "assets", "template.html");

    public static void main(String[] args) throws IOException, EmailException {
        HtmlEmail email = new HtmlEmail();
        email.setSmtpPort(PORT);
        email.setHostName(HOSTNAME);

        StringBuilder html = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(HTML_PATH.toString()))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null)
                html.append(currentLine);
        }

        try (BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.printf("%sUsername: %s", "\u001B[36m", "\u001B[0m");
            String user = stdIn.readLine();
            System.out.printf("%sPassword: %s", "\u001B[36m", "\u001B[0m");
            String pass = stdIn.readLine();

            email.setAuthenticator(new DefaultAuthenticator(user, pass));
            email.setSSLOnConnect(true)
                 .addTo(RECIPIENT, "Juan Agüí Martín")
                 .setFrom(user + "@educa.madrid.org", "Daniel Muñoz Neyra")
                 .setSubject("Correo HTML")
                 .setMsg("This is a HTML Email :-)");
            email.setHtmlMsg(html.toString())
                 .setTextMsg("Your email client does not support HTML messages")
                 .send();
        }
    }
}
