package com.example.springbootmq.rabbitMQ.controller;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 */
@Component
public class RabbitMQReceiver {

    @RabbitListener(queues = "hello.queue1")
    @RabbitHandler
    public void receiver1(String msg){
        System.out.println("消费者1接收到hello.queue1消息：" + msg);
    }


}
