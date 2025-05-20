package com.tokseg.storage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.tokseg.storage.domain.email.DTOs.EmailDTO;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServices {

    @Autowired
    JavaMailSender mailSender;

    public void sendEmail(EmailDTO email) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setFrom("noreplay.storage@tokseg.com");
            helper.setTo(email.to());
            helper.setSubject(email.subject());

            if (email.isHtml()) {
                helper.setText(email.body(), true);
            } else {
                helper.setText(email.body(), false);
            }

            mailSender.send(mimeMessage);

        } catch (MessagingException e) {

            throw new RuntimeException("Erro ao enviar e-mail", e);
        }
    }

}
