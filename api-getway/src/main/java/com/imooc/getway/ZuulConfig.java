//package com.imooc.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
//import org.springframework.stereotype.Component;
//
///**
// * 动态配置zuul
// * Create by Jack SD
// * Date 2019/9/29 0029 16:20
// */
//@Component
//public class ZuulConfig {
//
//    @ConfigurationProperties("zuul")
//    @RefreshScope
//    public ZuulProperties zuulProperties() {
//        return new ZuulProperties();
//    }
//}
