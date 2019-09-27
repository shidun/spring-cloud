package com.imooc.product.exception;

/**
 * Create by Jack SD
 * Date 2019/9/26 0026 18:20
 */
public class ProductException extends RuntimeException {

    private Integer code;

    public ProductException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
}
