package com.lzhs.services;

import com.lzhs.dto.OrderDTO;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/20 : 5:11 PM<br/>
 */
public interface PayService {
    void create(OrderDTO orderDTO);
}
