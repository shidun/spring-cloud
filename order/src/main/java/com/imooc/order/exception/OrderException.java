package com.imooc.order.exception;

/**
 * Create by Jack SD
 * Date 2019/9/26 0026 14:47
 */
public class OrderException extends RuntimeException {
    private Integer code;

    public OrderException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public OrderException() {

    }
}
