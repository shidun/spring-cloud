package com.imooc.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @RequestMapping("/getProduct")
    @HystrixCommand(commandProperties = {
//            设置超时时间
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String getProduct() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://127.0.0.1:8084/product/listForOrder", Arrays.asList("1565404863372550529"), String.class);
    }

    public String callBack() {
        return "休息一会 马上回来！";
    }

    public String defaultBack() {
        return "默认返回 休息一会";
    }
}
