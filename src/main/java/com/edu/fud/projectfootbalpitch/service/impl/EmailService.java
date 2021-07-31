package com.edu.fud.projectfootbalpitch.service.impl;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {
    public boolean sendEmail(String subject,String message,String to){
        boolean f = false;
        String from = "thod561@gmail.com";
        String host = "smtp.gmail.com";
        Properties properties=System.getProperties();
        System.out.println("Properties "+properties);
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");
        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("zak.shiro@gmail.com","thangchami269");
            }
        });
        session.setDebug(true);
        Message message1=new MimeMessage(session);
        try {
            message1.setFrom(new InternetAddress(from));
            message1.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message1.setSubject(subject);
            //message1.setText(message);
            message1.setContent(message,"text/html; charset=UTF-8");
            Transport.send(message1);
            System.out.println("Sent success...");
            f=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return f;
    }
}
