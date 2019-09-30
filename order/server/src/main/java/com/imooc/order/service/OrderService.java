package com.imooc.order.service;

import com.imooc.order.dto.OrderDTO;

import java.util.List;

/**
 * Create by Jack SD
 * Date 2019/9/26 0026 14:29
 */
public interface OrderService {

    OrderDTO save(OrderDTO orderDTO);

    /**
     * 完结订单只能卖家操作
     * @param orderId
     * @return
     */
    OrderDTO finish(String orderId);

}
