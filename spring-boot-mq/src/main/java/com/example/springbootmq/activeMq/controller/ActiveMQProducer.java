package com.example.springbootmq.activeMq.controller;

import com.example.springbootmq.activeMq.domin.StudentDO;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Topic;
import java.io.Serializable;

/**
 * ActiveMq
 * activeMQ队列在离线的重新上线只有一个消费者消费信息
 */
@RestController
public class ActiveMQProducer {

    // spring boot是自动配置的  如果改成spring需要xml文件
    @Resource
    JmsMessagingTemplate jmsMessagingTemplate;

    @Resource(name = "queueAMQ")
    Queue queue;

    @Resource(name = "queueAMQ1")
    Queue queue1;

    @Resource
    Topic topic;

    /**
     * 点对点发送消息
     * @param msg
     */
    @RequestMapping("/send")
    public void sendMsg(String msg) {
        System.out.println("发送队列消息内容 :" + msg);

        this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
    }

    /**
     * 订阅模式
     * 发送消息
     */
    @RequestMapping("/sendTopic")
    public void sendTopic(String msg){
        System.out.println("发布订阅消息" + msg);
        this.jmsMessagingTemplate.convertAndSend(this.topic,msg);
    }

    // 发送对象
    @RequestMapping("/sendObject")
    public void sendMsg() throws JMSException {
        StudentDO studentDO = new StudentDO();
        studentDO.setName("小明");
        studentDO.setAge(18);
        studentDO.setSex("男");
        System.out.println("发送队列消息内容 :" + studentDO);
        ActiveMQObjectMessage objectMessage = new ActiveMQObjectMessage();
        objectMessage.setObject(studentDO);
        // 设置特有标志
        objectMessage.setIntProperty("id",1);
        this.jmsMessagingTemplate.convertAndSend(this.queue1, objectMessage);
    }

}
