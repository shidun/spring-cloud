package com.imooc.getway.exception;

/**
 * Create by Jack SD
 * Date 2019/9/29 0029 17:17
 */
public class GetWayException extends RuntimeException {

    private Integer code;

    private String msg;

    public GetWayException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
    public GetWayException() {
        super();
    }
}
