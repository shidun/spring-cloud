package com.imooc.product.service.impl;

import com.imooc.product.ProductApplicationTests;
import com.imooc.product.dataobject.ProductCategory;
import com.imooc.product.dataobject.ProductInfo;
import com.imooc.product.dto.CartDto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Create by Jack SD
 * Date 2019/9/26 0026 10:19
 */
@Component
public class ProductServiceImplTest extends ProductApplicationTests {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findByProductStatus() {
        List<ProductInfo> productInfoList = productService.findUpAll();
        Assert.assertNotEquals(0, productInfoList.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> productCategoryList = productService.findByCategoryTypeIn(Arrays.asList(11, 22));
        Assert.assertNotEquals(0, productCategoryList.size());
    }

    @Test
    public void findByProductIdIn() {
        List<ProductInfo> productInfoList = productService.findByProductIdIn(Arrays.asList("1234567", "1554089994111308244"));
        Assert.assertNotEquals(0, productInfoList.size());
    }

    @Test
    public void decreaseStock() {
        CartDto cartDto = new CartDto("1554089994111308244", 10);
        productService.decreaseStock(Arrays.asList(cartDto));
    }
}