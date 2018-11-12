package com.lzhs.data_object;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Description: 商品 表与之对应实例 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/8 : 2:09 PM<br/>
 */
@Entity
@Data
public class ProductInfo {
    /**
     * 主键
     */
    @Id
    private String productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品单价
     */
    private BigDecimal productPrice;
    /**
     * 商品库存
     */
    private int productStock;
    /**
     * 商品描述
     */
    private String productDescription;
    /**
     * 商品图片
     */
    private String productIcon;
    /**
     * 商品状态,0正常1下架
     */
    private int productStatus;
    /**
     * 商品类目
     */
    private Integer categoryType;

    /**
     * 该商品记录创建时间
     */
    private Date createTime;
    /**
     * 商品最后修改时间
     */
    private Date updateTime;
}
