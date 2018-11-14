package com.lzhs.dto;

import com.lzhs.data_object.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Description: 与订单表与之对应的商品表 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/14 : 3:18 PM<br/>
 */
@Data
public class OrderDTO {
    /**
     * 订单ID
     */
    private String orderId;
    /**
     * 买家姓名
     */
    private String buyerName;
    /**
     * 买家电话
     */
    private String buyerIphone;
    /**
     * 买家地址
     */
    private String buyerAddress;
    /**
     * 买家微信
     */
    private String buyerOpenid;
    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;
    /**
     * 订单状态，0代表默认新订单
     */
    private Integer orderStatus;
    /**
     * 支付状态 0代表默认值未支付
     */
    private Integer payStatus ;

    /**
     * 该商品记录创建时间
     */
    private Date createTime;
    /**
     * 商品最后修改时间
     */
    private Date updateTime;
    /**
     * 该订单对应的商品列表
     */
    private List<OrderDetail> orderDetailList;
}
