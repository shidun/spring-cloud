package com.imooc.product.Controller;

import com.imooc.product.dataobject.ProductCategory;
import com.imooc.product.dataobject.ProductInfo;
import com.imooc.product.dto.CartDto;
import com.imooc.product.service.ProductService;
import com.imooc.product.utils.ResultVoUtil;
import com.imooc.product.vo.ProductInfoVO;
import com.imooc.product.vo.ProductVO;
import com.imooc.product.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 查询所有在架的商品
     * 获取类目type列表
     * 查询类目
     * 构造数据
     */
    @GetMapping("/list")
//    @CrossOrigin(allowCredentials = "true")
    public ResultVO list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                         @RequestParam(value = "size", defaultValue = "10") Integer size,
                         Map<String , Object> map) {
//        PageRequest request = new PageRequest(page - 1, size);
        List<ProductInfo> productInfoList = productService.findUpAll();
        List<Integer> categoryTypes = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = productService.findByCategoryTypeIn(categoryTypes);
        List<ProductVO> productVOS = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOS = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOS.add(productInfoVO);
                }
            }
            productVO.setFoods(productInfoVOS);
            productVOS.add(productVO);
        }
        return ResultVoUtil.success(productVOS);
    }

    @PostMapping("/listForOrder")
    public List<ProductInfo> listForOrder(@RequestBody List<String> productIds) {
        return productService.findByProductIdIn(productIds);
    }

    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<CartDto> cartDtos) {
         productService.decreaseStock(cartDtos);
    }
}
