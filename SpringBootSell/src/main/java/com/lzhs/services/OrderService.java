package com.lzhs.services;

import com.lzhs.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/8 : 1:16 AM<br/>
 */
public interface OrderService {

    /**
     * 创建订单
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 查询单个订单
     */
    OrderDTO findById(String orderId);

    /**
     * 查询该商户所有订单
     */
    Page<OrderDTO> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
    /**
     * 查询所有订单
     */
    Page<OrderDTO> findByBuyerAll(Pageable pageable);

    /**
     * 取消订单
     */
    OrderDTO cancel(OrderDTO orderDTO);

    /**
     * 完结订单
     */
    OrderDTO finish(OrderDTO orderDTO);

    /**
     * 支付订单
     */
    OrderDTO paid(OrderDTO orderDTO);

}
