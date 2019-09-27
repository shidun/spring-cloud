package com.imooc.order.service.impl;

import com.imooc.order.dataobject.OrderDetail;
import com.imooc.order.dto.OrderDTO;
import com.imooc.order.enums.PayStatusEnum;
import com.imooc.order.service.OrderService;
import com.imooc.product.client.ProductClient;
import com.imooc.order.dataobject.OrderMaster;
import com.imooc.order.enums.OrderStatusEnum;
import com.imooc.order.repository.OrderDetailRepository;
import com.imooc.order.repository.OrderMasterRepository;
import com.imooc.order.util.KeyUtil;
import com.imooc.product.common.DecreaseStockInput;
import com.imooc.product.common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by Jack SD
 * Date 2019/9/26 0026 14:31
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private ProductClient productClient;

    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        /**
         * 查询商品信息（调用商品服务）
         * 计算总价
         * 扣库存
         * 订单入库
         */
        String orderId = KeyUtil.getRadom();
        List<String> productIds = orderDTO.getOrderDetailList().stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        List<ProductInfoOutput> productInfos = productClient.listForOrder(productIds);
        BigDecimal orderAmout = new BigDecimal("0");
        for (OrderDetail orderDetail: orderDTO.getOrderDetailList()) {
            for (ProductInfoOutput productInfo: productInfos) {
                if (orderDetail.getProductId().equals(productInfo.getProductId())) {
                    orderAmout = (productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()))).add(orderAmout);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.getRadom());
                    orderDetailRepository.save(orderDetail);
                }
            }
        }
        List<DecreaseStockInput> cartDtoList = orderDTO.getOrderDetailList().stream().map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productClient.decreaseStock(cartDtoList);

        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmout(orderAmout);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
