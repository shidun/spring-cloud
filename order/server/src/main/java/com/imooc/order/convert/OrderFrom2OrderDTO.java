package com.imooc.order.convert;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imooc.order.dataobject.OrderDetail;
import com.imooc.order.dto.OrderDTO;
import com.imooc.order.exception.OrderException;
import com.imooc.order.form.OrderForm;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Jack SD
 * Date 2019/9/26 0026 14:53
 */
public class OrderFrom2OrderDTO {

    public static OrderDTO convertOrderFrom(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setBuyerAdress(orderForm.getAddress());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (Exception e) {
            throw new OrderException(0, "json转成出错");
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
