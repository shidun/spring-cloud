package com.imooc.order.controller;

import com.imooc.order.dataobject.ProductInfo;
import com.imooc.order.dto.CartDto;
import com.imooc.product.client.ProductClient;
import com.imooc.product.common.DecreaseStockInput;
import com.imooc.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Create by Jack SD
 * Date 2019/9/26 0026 16:21
 */
@RestController
@Slf4j
public class ClientController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private ProductClient productClient;

//    @Autowired
//    private RestTemplate restTemplate;

    @RequestMapping("/decreaseStock")
    public String getdecreaseStock() {
        DecreaseStockInput cartDto =  new DecreaseStockInput("1554089994111308244", 10);
        productClient.decreaseStock(Arrays.asList(cartDto));
        return  "ok";
    }

    @RequestMapping("/productList")
    public String getlistForOrder() {
        List<ProductInfoOutput> productInfoList =  productClient.listForOrder(Arrays.asList("1234567", "1554089994111308244"));
        log.info("result=:{}", productInfoList);
        return  "11";
    }
//
//    @RequestMapping("/productMsg")
//    public String getMsg() {
//        return productClient.getProductMsg();
//    }

    @RequestMapping("/getProductMsg")
    public String getProductMsg() {
        //直接使用restTemplate
//        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject("http://127.0.0.1:8081/server/msg", String.class);
        //使用LoadBalancerClient
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance serviceInstance =loadBalancerClient.choose("PRODUCT");
        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()) + "/server/msg";
        String result = restTemplate.getForObject(url, String.class);
        //@LoadBalanced注解
//        String result = restTemplate.getForObject("http://PRODUCT/server/msg", String.class);
        return result;
    }
}
