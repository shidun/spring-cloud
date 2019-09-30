package com.imooc.user.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Create by Jack SD
 * Date 2019/9/26 0026 10:41
 */
@Data
public class ProductVO {
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;

    private List<ProductInfoVO> foods;
}
