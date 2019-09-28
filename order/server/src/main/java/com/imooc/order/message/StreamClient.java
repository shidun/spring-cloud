package com.imooc.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Create by Jack SD
 * Date 2019/9/28 0028 17:30
 */
public interface StreamClient {
    @Input("myMessagesInput")
    SubscribableChannel input();

    @Output("myMessagesOutPut")
    MessageChannel outPut();
}
