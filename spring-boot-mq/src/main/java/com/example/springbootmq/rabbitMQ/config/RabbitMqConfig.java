package com.example.springbootmq.rabbitMQ.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMq实现的是AMQP消息队列协议，从概念上来讲，AMQP消息路由必须有三部分：交换器、队列和绑定。
 *  生产者把消息发布到交换器上；消息最终达到队列，并被消费者接收；绑定决定了消息如何从路由器由到特定的队列。
 * RabbitMQ有几个基本概念
 *
 *
 * 创建队列，然后交换器可以绑定这个队列，多个交换器可以绑定不同的队列（相当于广播了，不过需要匹配到特定的队列），多个队列也可以绑定不同的交换器。
 * 交换器绑定队列后，要发送到哪一个队列呢？这时候就需要一个路由键（routing key），来标识，不同类型的交换器，路由键的规则也不一样
 * 一共有四种类型：direct、fanout、topic和headers
 *
 */
@Configuration
public class RabbitMqConfig {

    // 声明队列
    @Bean
    public Queue queue(){
        // true表示持久化该队列
        return new Queue("hello.queue1",true);
    }

    /**
     * 交换器，类型是topic
     * topic 交换器通过模式匹配分配消息的路由键属性，将路由键和某个模式进行匹配，此时队列需要绑定到一个模式上。
     * 它将路由键和绑定键的字符串切分成单词，这些单词之间用点隔开。它同样也会识别两个通配符：符号“#”和符号“”。
     * #匹配0个或多个单词，匹配不多不少一个单词。
     *
     * @return
     */
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topicExchange");
    }

    /**
     * 交换器 direct
     * @return
     */
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("directExchange");
    }

    /**
     * 将queue和topicExchange进行绑定
     * @return
     */
    @Bean
    public Binding binding(){
        //用的topic类型，匹配的是key.#
        return BindingBuilder.bind(this.queue()).to(topicExchange()).with("key.#");
    }

    /**
     * 将queue和directExchange进行绑定
     * @return
     */
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(this.queue()).to(directExchange()).with("key.1");
    }

}
