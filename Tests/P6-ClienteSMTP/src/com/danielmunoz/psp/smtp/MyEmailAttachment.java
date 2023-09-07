package com.danielmunoz.psp.smtp;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Path;

public class MyEmailAttachment {
    private static final int    PORT      = 465;
    private static final String HOSTNAME  = "smtp.educa.madrid.org";
    private static final String RECIPIENT = "juan.agui2@educa.madrid.org";

    public static void main(String[] args) throws IOException, EmailException {
        MultiPartEmail email = new MultiPartEmail();
        email.setSmtpPort(PORT);
        email.setHostName(HOSTNAME);

        EmailAttachment attachmentLocal = new EmailAttachment();
        attachmentLocal.setPath(Path.of(System.getProperty("user.dir"), "assets", "attachment.png").toString());
        attachmentLocal.setDisposition(EmailAttachment.ATTACHMENT);
        attachmentLocal.setDescription("Logo of SMTP");
        attachmentLocal.setName("Local File");

        EmailAttachment attachmentURL = new EmailAttachment();
        attachmentURL.setURL(new URL("https://wpmailsmtp.com/wp-content/uploads/2019/06/wp-mail-smtp-mascot.png"));
        attachmentURL.setDisposition(EmailAttachment.ATTACHMENT);
        attachmentURL.setDescription("Mascot of SMTP");
        attachmentURL.setName("URL File");

        try (BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.printf("%sUsername: %s", "\u001B[36m", "\u001B[0m");
            String user = stdIn.readLine();
            System.out.printf("%sPassword: %s", "\u001B[36m", "\u001B[0m");
            String pass = stdIn.readLine();

            email.setAuthenticator(new DefaultAuthenticator(user, pass));
            email.setSSLOnConnect(true)
                 .addTo(RECIPIENT, "Juan Agüí Martín")
                 .setFrom(user + "@educa.madrid.org", "Daniel Muñoz Neyra")
                 .setSubject("Correo con Adjuntos")
                 .setMsg("This is an Email Attachment :-)");
            email.attach(attachmentLocal)
                 .attach(attachmentURL)
                 .send();
        }
    }
}
