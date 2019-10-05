package com.imooc.product.server.service;

import com.imooc.product.server.dataobject.ProductCategory;
import com.imooc.product.server.dataobject.ProductInfo;
import com.imooc.product.server.dto.CartDto;

import java.util.List;

/**
 * Create by Jack SD
 * Date 2019/9/26 0026 10:11
 */
public interface ProductService {

    List<ProductInfo> findUpAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypes);

    List<ProductInfo> findByProductIdIn(List<String> productIds);

    void decreaseStock(List<CartDto> cartDtos);
}
