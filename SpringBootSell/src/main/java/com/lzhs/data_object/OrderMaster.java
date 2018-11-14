package com.lzhs.data_object;

import com.lzhs.enums.OrderStatusEnum;
import com.lzhs.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Description: 商家订单主表 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/14 : 10:32 AM<br/>
 */
@Data
@Entity
@DynamicUpdate
public class OrderMaster {
    /**
     * 订单ID
     */
    @Id
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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    /**
     * 支付状态 0代表默认值未支付
     */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /**
     * 该商品记录创建时间
     */
    private Date createTime;
    /**
     * 商品最后修改时间
     */
    private Date updateTime;
}
