package com.danielmunoz.psp.smtp;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class MyImageHtmlEmail {
    private static final int    PORT      = 465;
    private static final String HOSTNAME  = "smtp.educa.madrid.org";
    private static final String RECIPIENT = "juan.agui2@educa.madrid.org";

    public static void main(String[] args) throws IOException, EmailException {
        ImageHtmlEmail email = new ImageHtmlEmail();
        email.setSmtpPort(PORT);
        email.setHostName(HOSTNAME);

        URL url = new URL("https://wpmailsmtp.com");
        email.setDataSourceResolver(new DataSourceUrlResolver(url));
        String html = "<img src=\"https://wpmailsmtp.com/wp-content/uploads/2019/06/wp-mail-smtp-mascot.png\">";

        try (BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.printf("%sUsername: %s", "\u001B[36m", "\u001B[0m");
            String user = stdIn.readLine();
            System.out.printf("%sPassword: %s", "\u001B[36m", "\u001B[0m");
            String pass = stdIn.readLine();

            email.setAuthenticator(new DefaultAuthenticator(user, pass));
            email.setSSLOnConnect(true)
                 .addTo(RECIPIENT, "Juan Agüí Martín")
                 .setFrom(user + "@educa.madrid.org", "Daniel Muñoz Neyra")
                 .setSubject("Correo HTML con Imágenes")
                 .setMsg("This is an Image HTML Email :-)");
            email.setHtmlMsg(html)
                 .setTextMsg("Your email client does not support HTML messages")
                 .send();
        }
    }
}
