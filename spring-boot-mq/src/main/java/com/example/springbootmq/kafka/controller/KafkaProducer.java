package com.example.springbootmq.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 * Kafka生产者
 */
@RestController
public class KafkaProducer {

    @Autowired
    KafkaTemplate<String,Object> kafkaTemplate;

    public void send(){



    }

}
