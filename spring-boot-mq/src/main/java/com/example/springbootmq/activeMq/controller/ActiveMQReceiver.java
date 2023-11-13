package com.example.springbootmq.activeMq.controller;

import com.example.springbootmq.activeMq.domin.StudentDO;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

/**
 * 消费者
 */
//@Component
public class ActiveMQReceiver {

    /**
     * 点对点接收消息
     * 监听的队列名
     * destination = "sms.queue"
     * @param text
     */
    @JmsListener(destination = "sms.queue")
    public void receiveMsg(String text) throws InterruptedException {
        System.out.println("消费者1接收到队列消息 : "+text);
        Thread.sleep(10000);
    }

    // 使用的是默认的工厂类
    @JmsListener(destination = "sms.queue")
    public void receiveMsg1(String text) throws InterruptedException {
        System.out.println("消费者2接收到队列消息 : "+text);
        Thread.sleep(10000);
    }

    /**
     * 订阅消息
     * 接受订阅
     * 消费者1
     */
    @JmsListener(destination = "sms.topic")
    public void receiveTopic1(String text){
        System.out.println("消费者1接收订阅消息：" + text);
    }

    /**
     * 订阅消息
     * 接受订阅
     * 消费者2
     */
    @JmsListener(destination = "sms.topic")
    public void receiveTopic2(String text){
        System.out.println("消费者2接收订阅消息：" + text);
    }


    //  使用自定义的工厂类  来实现消息传递

    @JmsListener(destination = "sms.queue",containerFactory = "queueListenerFactory")
    public void receiveMsg3(String text) throws InterruptedException {
        System.out.println("消费者3接收到队列消息 : "+text);
        Thread.sleep(10000);
    }

    @JmsListener(destination = "sms.topic",containerFactory = "topicListenerFactory")
    public void receiveTopic3(String text){
        System.out.println("消费者3接收订阅消息：" + text);
    }

    // ====================接收对象====================
    @JmsListener(destination = "sms.queueObj")
    public void receiveMsg4(ObjectMessage message) throws InterruptedException, JMSException {
        // 需要在配置文件里加入spring.activemq.packages.trust-all=true
        message.getIntProperty("id");
        StudentDO studentDO = (StudentDO) message.getObject();
        System.out.println("消费者4接收到队列对象消息 : "+studentDO);
    }



}
