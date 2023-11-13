package com.example.springbootmq.activeMq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * ActiveMq默认只支持一种模式，要么点对点模式，要么发布/订阅模式
 *
 */
@Configuration
public class ActiveMqConfig {


    @Bean("queueAMQ")
    public Queue queue() {
        // 队列名
        return new ActiveMQQueue("sms.queue");
    }

    @Bean("queueAMQ1")
    public Queue queue1() {
        // 队列名
        return new ActiveMQQueue("sms.queueObj");
    }

    @Bean
    public Topic topic(){
        // 订阅模式
        return new ActiveMQTopic("sms.topic");
    }

    /**
     * 如果要支持两种模式，要重写一下JmsListenerContainerFactory，去实现两种模式
     */

    /**
     * 点对点
     */
    @Bean("queueListenerFactory")
    public JmsListenerContainerFactory queueListenerFactory(ConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        // 设置消息类型
        factory.setPubSubDomain(false);
        return factory;
    }

    /**
     * 发布/订阅
     */
    @Bean("topicListenerFactory")
    public JmsListenerContainerFactory topicListenerFactory(ConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        // 设置消息类型
        factory.setPubSubDomain(true);
        return factory;
    }


}
