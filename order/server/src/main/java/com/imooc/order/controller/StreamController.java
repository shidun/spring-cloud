package com.imooc.order.controller;

import com.imooc.order.dto.OrderDTO;
import com.imooc.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Create by Jack SD
 * Date 2019/9/28 0028 17:32
 */
@RestController
public class StreamController {

    @Autowired
    private StreamClient streamClient;

    @RequestMapping("/test")
    public String test() {
        return  "order-test";
    }

    @RequestMapping("/sendMessagess")
    public void process() {
        String msg = "now " + new Date();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("5555555555");
        orderDTO.setBuyerAdress("撒大声地");
        streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());
    }

}
