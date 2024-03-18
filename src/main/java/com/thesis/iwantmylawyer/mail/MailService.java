package com.thesis.iwantmylawyer.mail;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailService {

    private final JavaMailSender mailSender;


    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public String sendMail(SendMailRequest sendMailRequest){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@metsoft.com");
        message.setTo(sendMailRequest.to());
        message.setText(sendMailRequest.text());
        message.setSubject(sendMailRequest.subject());
        mailSender.send(message);
        return "Gönderildi";
    }
}
