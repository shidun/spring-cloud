package com.imooc.product.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Create by Jack SD
 * Date 2019/9/26 0026 10:36
 */
@Data
public class ResultVO<T> implements Serializable {
    private static final long serialVersionUID = -1L;

    private Integer code;
    private String msg;
    private T data;
}
