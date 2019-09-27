package com.imooc.order.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品详情表
 */
@Entity
@Data
@DynamicUpdate
public class ProductInfo implements Serializable {
    private static final long serialVersionUID = -1L;
    @Id //表的主键
    private String productId;
    //名称
    private String productName;
    //价格
    private BigDecimal productPrice;
    //库存
    private Integer productStock;
    //描述
    private String productDescription;
    //小图
    private String productIcon;
    //状态  0正常 1下架
    private Integer productStatus;
    //类目编号
    private Integer categoryType;

    private Date createTime;

//    @JsonIgnore
//    public ProductStatusEnum getProductStatusEnum() {
//        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
//    }
}
