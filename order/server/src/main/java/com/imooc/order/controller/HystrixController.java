package com.imooc.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @author : jack sd
 * @date : 2019/9/30
 */
@RestController
@DefaultProperties(defaultFallback = "defaultBack")
public class HystrixController {

//    @HystrixCommand(fallbackMethod = "backupCall")
//    @HystrixCommand
    public String callBack() {
        return "休息一会 马上回来！";
    }


//    @HystrixCommand(commandProperties = {
////            设置超时时间
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
//    })
    @HystrixCommand(
//            fallbackMethod = "callBack" ,
            //熔断器
            commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
    })
//    @HystrixCommand
    @RequestMapping("/getProduct")
    public String getProduct(@RequestParam("num") Integer num) {
        if (num == 1) {
            return "success";
        }
        throw new RuntimeException("1111");
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.postForObject("http://127.0.0.1:8084/product/listForOrder", Arrays.asList("1565404863372550529"), String.class);
    }

    public String defaultBack() {
        return "默认返回 休息一会";
    }
}
