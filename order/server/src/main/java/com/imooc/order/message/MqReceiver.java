package com.imooc.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Create by Jack SD
 * Date 2019/9/28 0028 16:23
 */
@Component
@Slf4j
public class MqReceiver {

    @RabbitListener(queuesToDeclare = @Queue("myQueues"))
    public void process(String message) {
        log.info("MqReceiver:{}", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("orderQueue"),
            exchange = @Exchange("orderExchange")
    ))
    public void orderProcess(String message) {
        log.info("orderProcess:{}", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("productExchange"),
            key = "product",
            value = @Queue("productQueue")

    ))
    public void productProcess(String message) {
        log.info("productProcess:{}", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("productExchange"),
            key = "food",
            value = @Queue("foodQueue")
    ))
    public void foodProcess(String message) {
        log.info("foodProcess:{}", message);
    }
}
