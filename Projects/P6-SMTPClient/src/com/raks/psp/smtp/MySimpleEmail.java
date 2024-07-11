package com.raks.psp.smtp;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MySimpleEmail {

    private static final int    PORT      = 465;
    private static final String HOSTNAME  = "smtp.educa.madrid.org";
    private static final String RECIPIENT = "juan.agui2@educa.madrid.org";

    public static void main(String[] args) throws IOException, EmailException {
        Email email = new SimpleEmail();
        email.setSmtpPort(PORT);
        email.setHostName(HOSTNAME);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.printf("%sUsername: %s", "\u001B[36m", "\u001B[0m");
            String user = br.readLine();
            System.out.printf("%sPassword: %s", "\u001B[36m", "\u001B[0m");
            String pass = br.readLine();

            email.setAuthenticator(new DefaultAuthenticator(user, pass));
            email.setSSLOnConnect(true)
                 .addTo(RECIPIENT)
                 .setFrom(user + "@educa.madrid.org")
                 .setSubject("Correo Simple")
                 .setMsg("This is a Simple Email :-)")
                 .send();
        }
    }

}