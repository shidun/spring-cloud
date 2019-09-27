package com.imooc.order.dto;

import com.imooc.order.dataobject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Create by Jack SD
 * Date 2019/9/26 0026 14:30
 */
@Data
public class OrderDTO {
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAdress;
    private String buyerOpenid;
    private BigDecimal orderAmout;
    //订单状态 默认为0新下单
    private Integer orderStatus ;
    //支付状态 默认为0未支付
    private Integer payStatus;

    private Date createTime;
    private Date updateTime;

    private List<OrderDetail> orderDetailList = new ArrayList<>();
}
