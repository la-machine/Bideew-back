package com.example.bideew.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.bideew.Dto.ContactMessage;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailSender {

     private final static Logger LOGGER = LoggerFactory
            .getLogger(EmailSender.class);
    private final JavaMailSender mailSender;

     public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom("bideew@gmail.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }
    }

    public void sendEmail(ContactMessage contactForm) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(contactForm.getEmail()); // Set the "From" address to the provided value
        message.setTo("loicyoualeu@gmail.com");
        message.setSubject("New Contact Form Submission From Bideew");
        message.setText("Name: " + contactForm.getName() +
                        "\nEmail: " + contactForm.getEmail() +
                        "\nMessage: " + contactForm.getMessage());

        mailSender.send(message);
    }
}
