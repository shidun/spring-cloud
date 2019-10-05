package com.imooc.product.server.dto;

import lombok.Data;

/**
 * Create by Jack SD
 * Date 2019/9/26 0026 18:17
 */
@Data
public class CartDto {

    private String productId;
    private Integer productQuantity;

    public CartDto() {

    }

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
