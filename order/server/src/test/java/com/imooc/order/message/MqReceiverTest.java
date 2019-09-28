package com.imooc.order.message;

import com.imooc.server.ServerApplicationTests;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.junit.Assert.*;

/**
 * Create by Jack SD
 * Date 2019/9/28 0028 16:25
 */
@Component
public class MqReceiverTest extends ServerApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void process() {
        amqpTemplate.convertAndSend("myQueues", "time:" + System.currentTimeMillis());
    }

    @Test
    public void orderProcess() {
        rabbitTemplate.convertAndSend("orderQueue", "orderProcess :" + System.currentTimeMillis());
//        amqpTemplate.convertAndSend("orderExchange", "orderProcess :" + System.currentTimeMillis());
    }

    @Test
    public void productProcess() {
        rabbitTemplate.convertAndSend("productExchange", "product", "productProcess:" + System.currentTimeMillis());
//        amqpTemplate.convertAndSend("productExchange", "product", "productProcess:" + System.currentTimeMillis());
    }
    @Test
    public void foodProcess() {
        rabbitTemplate.convertAndSend("productExchange", "food", "foodProcess:" + System.currentTimeMillis());
//        amqpTemplate.convertAndSend("productExchange", "food", "foodProcess:" + System.currentTimeMillis());
    }
}