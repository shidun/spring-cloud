package com.imooc.user.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Create by Jack SD
 * Date 2019/9/26 0026 10:43
 */
@Data
public class ProductInfoVO {
    @JsonProperty("id")
    private String productId;
    @JsonProperty("name")
    private String productName;
    @JsonProperty("price")
    private BigDecimal productPrice;
    @JsonProperty("decription")
    private String productDescription;
    @JsonProperty("icon")
    private String productIcon;
}
