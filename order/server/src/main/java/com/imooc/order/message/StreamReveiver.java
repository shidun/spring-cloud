package com.imooc.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * Create by Jack SD
 * Date 2019/9/28 0028 17:31
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReveiver {

    @StreamListener("myMessagesOutPut")
    public void process(Object message) {
      log.info("StreamReveiver:{}", message);
    }
}
