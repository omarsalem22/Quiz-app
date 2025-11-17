package com.example.Quiz.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void  sendTestEmail(String subject,String body) {

        try {
            SimpleMailMessage message=new SimpleMailMessage();
            message.setFrom(this.sender);
            message.setTo("ayaebrahimsa@gmail.com");
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}