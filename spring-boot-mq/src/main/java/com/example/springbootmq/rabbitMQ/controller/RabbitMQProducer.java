package com.example.springbootmq.rabbitMQ.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * RabbitMq
 */
@RestController
@RequestMapping("/rmq")
public class RabbitMQProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    // topicExchange 发送消息
    @RequestMapping("/sendTopic")
    public void sendByTopic(String msg){
        System.out.println("生产者Topic发送消息：" + msg);
        // 参数  交换机  绑定  消息
        rabbitTemplate.convertAndSend("topicExchange","key.1",msg);
    }

    // directExchange 发送消息
    @RequestMapping("/sendDirect")
    public void sendByDirect(String msg){
        System.out.println("生产者direct发送消息：" + msg);
        // 参数  交换机  绑定  消息
        rabbitTemplate.convertAndSend("directExchange","key.1",msg);
    }



}
