package com.imooc.product.server.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Create by Jack SD
 * Date 2019/9/29 0029 15:17
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(HttpServletRequest request) {
        return  "product-test";
    }
}
