package com.raks.swiftly.infrastructure.adapter;

import com.raks.swiftly.domain.model.dto.EmailDto;
import com.raks.swiftly.domain.port.spi.EmailJpaPort;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class EmailJpaAdapter implements EmailJpaPort {

    private final TemplateEngine _templateEngine;
    private final JavaMailSender _javaMailSender;

    @Override
    public void sendEmail(EmailDto dto) {
        try {
            MimeMessage       message = _javaMailSender.createMimeMessage();
            MimeMessageHelper helper  = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            Context           context = new Context();

            context.setVariables(dto.getProperties());
            String html = _templateEngine.process(dto.getTemplate(), context);

            helper.setTo(dto.getTo());
            helper.setFrom(dto.getFrom());
            helper.setSubject(dto.getSubject());
            helper.setText(html, true);
            _javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}