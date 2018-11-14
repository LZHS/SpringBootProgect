package com.lzhs.converters;

import com.lzhs.data_object.OrderMaster;
import com.lzhs.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/14 : 10:01 PM<br/>
 */
public class OrderMaster2OrderDTOConverter {
    public static OrderDTO convert(OrderMaster master) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(master, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> masters){
        return masters.stream()
                .map(master -> convert(master))
                .collect(Collectors.toList());
    }
}
