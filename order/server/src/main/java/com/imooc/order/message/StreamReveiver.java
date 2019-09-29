package com.imooc.order.message;

import com.imooc.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * Create by Jack SD
 * Date 2019/9/28 0028 17:31
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReveiver {

//    @StreamListener(StreamClient.INPUT)
//    public void process(OrderDTO message) {
//      log.info("StreamReveiverINPUT:{}", message);
//      try {
//          Thread.sleep(100000);
//      } catch (Exception e) {
//      }
//    }

    @StreamListener(StreamClient.INPUT)
    @SendTo(StreamClient.OUTPUT)
    public String process(Object message) {
      log.info("StreamReveiverINPUT:{}", message);
      return "received.";
    }

    @StreamListener(value = StreamClient.OUTPUT)
    public void process2(String message) {
        log.info("StreamReceiverOUTPUT: {}", message);
    }
}
