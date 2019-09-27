package com.imooc.order.controller;

import com.imooc.order.VO.ResultVO;
import com.imooc.order.convert.OrderFrom2OrderDTO;
import com.imooc.order.dto.OrderDTO;
import com.imooc.order.enums.ResultEnum;
import com.imooc.order.exception.OrderException;
import com.imooc.order.form.OrderForm;
import com.imooc.order.service.impl.OrderServiceImpl;
import com.imooc.order.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Create by Jack SD
 * Date 2019/9/26 0026 14:41
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("/create")
    public ResultVO create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new OrderException(ResultEnum.ORDER_PARAMS.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = orderService.save(OrderFrom2OrderDTO.convertOrderFrom(orderForm));
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            throw new OrderException(0, "购物车为空");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderDTO.getOrderId());
        return ResultVOUtil.success(map);
    }
}
