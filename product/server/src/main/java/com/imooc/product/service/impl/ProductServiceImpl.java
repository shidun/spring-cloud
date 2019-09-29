package com.imooc.product.service.impl;

import com.imooc.product.common.JsonUtil;
import com.imooc.product.common.ProductInfoOutput;
import com.imooc.product.dataobject.ProductInfo;
import com.imooc.product.dto.CartDto;
import com.imooc.product.exception.ProductException;
import com.imooc.product.dataobject.ProductCategory;
import com.imooc.product.enums.ProductStatusEnum;
import com.imooc.product.repository.ProductCategoryRepository;
import com.imooc.product.repository.ProductInfoRepository;
import com.imooc.product.service.ProductService;
import com.rabbitmq.tools.json.JSONUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Create by Jack SD
 * Date 2019/9/26 0026 10:11
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    @Transactional
    public void decreaseStock(List<CartDto> cartDtos) {
        for (CartDto cartDto : cartDtos) {
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(cartDto.getProductId());
            if (!productInfoOptional.isPresent()) {
                throw new ProductException(0, "订单不存在");
            }
            ProductInfo productInfo = productInfoOptional.get();
            Integer stock = productInfo.getProductStock() - cartDto.getProductQuantity();
            if (stock < 0) {
                throw new ProductException(0, "库存不够");
            }
            productInfo.setProductStock(stock);
            productInfoRepository.save(productInfo);
            ProductInfoOutput productInfoOutput = new ProductInfoOutput();
            BeanUtils.copyProperties(productInfo, productInfoOutput);

            rabbitTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutput));
        }
    }

    @Override
    public List<ProductInfo> findByProductIdIn(List<String> productIds) {
        return productInfoRepository.findByProductIdIn(productIds);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypes) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypes);
    }
}
