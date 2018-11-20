package com.lzhs.converters;

import com.lzhs.data_object.OrderDetail;
import com.lzhs.dto.OrderDTO;
import com.lzhs.enums.ResultEnum;
import com.lzhs.exceptions.SellRunException;
import com.lzhs.forms.OrderForm;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/14 : 10:01 PM<br/>
 */
public class OrderForm2OrderDTOConverter {
    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        try {
            List<OrderDetail> details = orderForm.getItems().stream().map(items -> {
                OrderDetail detail = new OrderDetail();
                detail.setProductQuantity(items.getProductQuantity());
                detail.setProductId(items.getProductId());
                return detail;
            }).collect(Collectors.toList());
            orderDTO.setBuyerName(orderForm.getName());
            orderDTO.setBuyerAddress(orderForm.getAddress());
            orderDTO.setBuyerIphone(orderForm.getPhone());
            orderDTO.setBuyerOpenid(orderForm.getOpenid());
            orderDTO.setOrderDetailList(details);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SellRunException(ResultEnum.PARAM_ERROR);
        }
        return orderDTO;
    }

}
