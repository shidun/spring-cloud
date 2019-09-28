package com.imooc.order.controller;

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

    @RequestMapping("/sendMessagess")
    public void process() {
        String msg = "now " + new Date();
        streamClient.outPut().send(MessageBuilder.withPayload(msg).build());
    }

}
