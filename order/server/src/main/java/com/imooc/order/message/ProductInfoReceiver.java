package com.imooc.order.message;

import com.imooc.product.common.JsonUtil;
import com.imooc.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Create by Jack SD
 * Date 2019/9/29 0029 11:41
 */
@Component
@Slf4j
public class ProductInfoReceiver {
    private static final String PRODUCT_STOCK = "product_stock_%s";
    @Autowired
    private RedisTemplate redisTemplate;

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message) {
        ProductInfoOutput productInfoOutput = (ProductInfoOutput)JsonUtil.fromJson(message, ProductInfoOutput.class);
        log.info("ProductInfoReceiver:{}", productInfoOutput);
        String key = String.format(PRODUCT_STOCK, productInfoOutput.getProductId());
        log.info("-----------------:{}---------------", key);
        redisTemplate.opsForValue().set(key, String.valueOf(productInfoOutput.getProductStock()));
    }
}
