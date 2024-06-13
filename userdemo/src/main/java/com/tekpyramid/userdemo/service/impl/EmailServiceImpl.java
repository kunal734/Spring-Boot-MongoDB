package com.tekpyramid.userdemo.service.impl;

import com.tekpyramid.userdemo.data.models.entity.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl {

    private final JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String body){
//        Email email=new Email();
        SimpleMailMessage mail=new SimpleMailMessage();
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(body);
        javaMailSender.send(mail);

    }
}
