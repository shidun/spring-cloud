package com.imooc.order.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * Create by Jack SD
 * Date 2019/9/26 0026 16:29
 */
@Component
@Configuration
public class RestemplateConfig {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 解决RedisTemplate乱码问题
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
}
