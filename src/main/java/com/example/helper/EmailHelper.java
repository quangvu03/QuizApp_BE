package com.example.helper;

import io.micronaut.email.Email;
import io.micronaut.email.EmailSender;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class EmailHelper {

    private final EmailSender<Email.Builder, Void> emailSender;

    @Inject
    public EmailHelper(EmailSender<Email.Builder, Void> emailSender) {
        this.emailSender = emailSender;
        System.out.println("EmailSender class: " + emailSender.getClass().getName()); // Debug
    }

    public void sendMail(String to, String subject, String body) {
        // Tạo Email.Builder nhưng không gọi .build()
        Email.Builder emailBuilder = Email.builder()
                .from("vutran08012k3@gmail.com")
                .to(to)
                .subject(subject)
                .body(body);

        try {
            emailSender.send(emailBuilder); // Truyền trực tiếp emailBuilder
            System.out.println("Email sent successfully to " + to);
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
            throw new RuntimeException("Email sending failed", e);
        }
    }
}