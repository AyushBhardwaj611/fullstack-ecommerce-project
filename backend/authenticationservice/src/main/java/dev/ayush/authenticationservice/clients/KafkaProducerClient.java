package dev.ayush.authenticationservice.clients;

import org.apache.naming.factory.SendMailFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerClient {

    private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerClient(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}

/*
the message is of the form string because the meessage is serialized
so we will give the JSON in the String
*/


//{
//    id:"",
//        name:""
//        email:""
//        }