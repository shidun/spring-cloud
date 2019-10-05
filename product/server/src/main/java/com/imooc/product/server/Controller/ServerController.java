package com.imooc.product.server.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by Jack SD
 * Date 2019/9/26 0026 16:18
 */
@RestController
@RequestMapping("/server")
public class ServerController {

    @RequestMapping("/msg")
    public String sendProductMsg() {
        return "this productInfo msg 1";
    }
}
