package com.imooc.order.client;

import com.imooc.order.dataobject.ProductInfo;
import com.imooc.order.dto.CartDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Create by Jack SD
 * Date 2019/9/26 0026 17:21
 */
@FeignClient(name = "product")
public interface ProductClient {

    @GetMapping("/server/msg")
    String getProductMsg();


    @GetMapping("/product/listForOrder")
    List<ProductInfo> listForOrder(@RequestBody List<String> productIds);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<CartDto> cartDtos);
}
