package com.lzhs.controllers;

import com.lzhs.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/20 : 5:05 PM<br/>
 */
@Controller
@RequestMapping("/pay")
public class PayController {
    @Autowired
    OrderService orderService;
    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl) {
        //查询订单
       // OrderDTO orderDTO = orderService.findById(orderId);


        // 支付订单
        Map<String ,String> params=new HashMap<>();
        params.put("orderId",orderId);
        params.put("returnUrl",returnUrl);

        return  new ModelAndView("/pay/test",params);

    }
}
