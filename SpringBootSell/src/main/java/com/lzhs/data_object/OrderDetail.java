package com.lzhs.data_object;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Description: 订单详情表 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/14 : 11:34 AM<br/>
 */
@Data
@Entity
@DynamicUpdate
public class OrderDetail {

    /**
     * 订单详情ID
     */
    @Id
    private  String detailId  ;
    /**
     * 商家订单ID
     */
    private  String orderId  ;
    /**
     * 商品 ID
     */
    private  String productId;
    /**
     * 商品名称
     */
    private  String productName  ;
    /**
     * 商品单价
     */
    private BigDecimal productPrice;
    /**
     * 商品图片
     */
    private  String productIcon  ;
    /**
     * 商品数量
     */
    private Integer productQuantity;
    /**
     * 该商品记录创建时间
     */
    private Date createTime;
    /**
     * 商品最后修改时间
     */
    private Date updateTime;

}
