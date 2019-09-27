package com.imooc.order.enums;

import lombok.Getter;

/**
 * Create by Jack SD
 * Date 2019/9/26 0026 14:49
 */
@Getter
public enum ResultEnum {
    ORDER_NULL(1, "订单为空"),
    ORDER_PARAMS(2, "参数错误"),
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
