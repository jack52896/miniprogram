package com.hdjtlgbbs.program.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventProducer {
    @Autowired
    KafkaTemplate kafkaTemplate;
//    public void send(String Topic, Event event){
//
//    }
}
