package com.project.emailservice.consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.emailservice.Dtos.SendEmailMessageDto;
import com.project.emailservice.utilities.EmailUtil;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class SendEmailConsumer {

    private ObjectMapper objectMapper;
    private EmailUtil emailUtil;

    public SendEmailConsumer(ObjectMapper objectMapper,
                             EmailUtil emailUtil) {
        this.objectMapper = objectMapper;
        this.emailUtil = emailUtil;
    }

    @KafkaListener(topics = "sendEmail", groupId = "emailService")
    public void handleSendEmail(String message) throws JsonProcessingException {
        SendEmailMessageDto sendEmailMessageDto = objectMapper.readValue(message, SendEmailMessageDto.class);

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ayushproject@gmail.com", "projectpassword");
                // the password here wwill be app genberated gmail password...check stack overflow
            }
        };
        Session session = Session.getInstance(props, auth);

        emailUtil.sendEmail(session, sendEmailMessageDto.getTo(), sendEmailMessageDto.getSubject(), sendEmailMessageDto.getBody());
    }

}

/*
groupId helps in identifying gthat a consumer from the consumer group has consumed the event
this is helpful when multiople instances of a server are running
*/
