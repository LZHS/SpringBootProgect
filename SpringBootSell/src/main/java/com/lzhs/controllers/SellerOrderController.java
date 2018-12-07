package com.lzhs.controllers;

import com.lzhs.exceptions.SellRunException;
import com.lzhs.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Description: 买家端Controller <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/29 : 4:34 PM<br/>
 */
@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {

    @Autowired
    OrderService service;

    /**
     * 获取订单列表
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ModelAndView getList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "size", defaultValue = "2") Integer size,
                                Map<String, Object> maps) {
        maps.put("orderDTOPage", service.findByBuyerAll(PageRequest.of(page - 1, size)));
        maps.put("currentPage", page);
        maps.put("size", size);

        return new ModelAndView("order/list", maps);
    }

    @GetMapping("cancel")
    public ModelAndView cancel(@RequestParam(value = "orderId") String orderId,
                               Map<String, Object> maps) {
        try {
            service.cancel(service.findById(orderId));
        } catch (SellRunException ex) {
            maps.put("msg", ex.getMessage());
            maps.put("url", "/SpringBootSell/seller/order/list");
            return new ModelAndView("common/error", maps);
        }

        maps.put("msg", "订单取消成功");
        maps.put("url", "/SpringBootSell/seller/order/list");
        return new ModelAndView("common/success", maps);
    }

}
