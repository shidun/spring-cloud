package com.imooc.order.VO;

import lombok.Data;

import java.io.Serializable;

/**
 * Create by Jack SD
 * Date 2019/9/26 0026 14:42
 */
@Data
public class ResultVO<T> implements Serializable {
    private static final Long serialVersionUID = -1L;
    private Integer code;
    private String msg;
    private T data;
}
