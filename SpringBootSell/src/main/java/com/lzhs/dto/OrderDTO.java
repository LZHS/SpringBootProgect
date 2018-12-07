package com.lzhs.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lzhs.data_object.OrderDetail;
import com.lzhs.enums.OrderStatusEnum;
import com.lzhs.enums.PayStatusEnum;
import com.lzhs.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

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
    private String orderId = "";
    /**
     * 买家姓名
     */
    private String buyerName = "";
    /**
     * 买家电话
     */
    private String buyerIphone = "";
    /**
     * 买家地址
     */
    private String buyerAddress = "";
    /**
     * 买家微信
     */
    private String buyerOpenid = "";
    /**
     * 订单总金额
     */
    private BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
    /**
     * 订单状态，0代表默认新订单
     */
    private Integer orderStatus = 0;
    /**
     * 支付状态 0代表默认值未支付
     */
    private Integer payStatus = 0;

    /**
     * 该商品记录创建时间
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    /**
     * 商品最后修改时间
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
    /**
     * 该订单对应的商品列表
     */
    private List<OrderDetail> orderDetailList = new ArrayList<>();

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum() {
        Optional<OrderStatusEnum> statusEnum = Arrays.stream(OrderStatusEnum.values())
                .filter(orderStatusEnum -> orderStatusEnum.getCode() == orderStatus)
                .findFirst();
        return statusEnum.isPresent() ? statusEnum.get() : null;
    }

    @JsonIgnore

    public PayStatusEnum getPayStatusEnum() {
        Optional<PayStatusEnum> anEnum = Arrays.stream(PayStatusEnum.values())
                .filter(payStatusEnum -> payStatusEnum.getCode() == payStatus)
                .findFirst();
        return anEnum.isPresent() ? anEnum.get() : null;
    }
}
