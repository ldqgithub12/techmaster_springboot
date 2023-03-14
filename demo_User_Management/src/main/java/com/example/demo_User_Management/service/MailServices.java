package com.example.demo_User_Management.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServices {
    private final JavaMailSender javaMailSender;

    public MailServices(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    public void sendMail(String receive_email, String subject, String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(receive_email);
        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);
    }
}
