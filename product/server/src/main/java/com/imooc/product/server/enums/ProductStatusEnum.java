package com.imooc.product.server.enums;

import lombok.Getter;

/**
 * Create by Jack SD
 * Date 2019/9/25 0025 20:40
 */
@Getter
public enum ProductStatusEnum {
    UP(1, "上架"),
    Down(0, "下架")
            ;

    private Integer code;
    private String msg;

    ProductStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
