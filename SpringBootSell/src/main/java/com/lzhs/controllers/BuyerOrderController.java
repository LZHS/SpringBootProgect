package com.lzhs.controllers;

import com.lzhs.VO.ResultVO;
import com.lzhs.converters.OrderForm2OrderDTOConverter;
import com.lzhs.dto.OrderDTO;
import com.lzhs.enums.ResultEnum;
import com.lzhs.exceptions.SellRunException;
import com.lzhs.forms.OrderForm;
import com.lzhs.services.OrderService;
import com.lzhs.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/15 : 2:26 PM<br/>
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    OrderService service;

    /**
     * 创建订单
     */
    @PostMapping(value = "/create")
    public ResultVO<Map<String, String>> create(@RequestBody @Valid OrderForm orderForm
            , BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new SellRunException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList()))
            throw new SellRunException(ResultEnum.PARAM_CART_ERROR);
        OrderDTO orderResult = service.create(orderDTO);
        Map<String, String> data = new HashMap<>();
        data.put("orderId", orderResult.getOrderId());
        return ResultVOUtil.success(data);
    }


    /**
     * 查询所有订单
     */
    @GetMapping(value = "/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openId") String openId,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "20") Integer size) {
        if (StringUtils.isEmpty(openId)) throw new SellRunException(ResultEnum.PARAM_ERROR);
        Page<OrderDTO> orderDTOPage = service.findByBuyerOpenid(openId, PageRequest.of(page, size));
        return ResultVOUtil.success(orderDTOPage.getContent());
    }

    /**
     * 查看订单详情
     */
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openId") String openId,
                                     @RequestParam("orderId") String orderId) {
        if (StringUtils.isEmpty(openId) || StringUtils.isEmpty(orderId))
            throw new SellRunException(ResultEnum.PARAM_ERROR);
        OrderDTO orderDTO = service.findById(orderId);
        if (!orderDTO.getBuyerOpenid().equals(openId))
            throw new SellRunException(ResultEnum.PARAM_NOT_JURISDICTION_ERROR);
        return ResultVOUtil.success(orderDTO);
    }

    /**
     * 取消订单
     */
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openId") String openId,
                           @RequestParam("orderId") String orderId) {
        if (StringUtils.isEmpty(openId) || StringUtils.isEmpty(orderId))
            throw new SellRunException(ResultEnum.PARAM_ERROR);
        OrderDTO orderDTO = service.findById(orderId);
        if (!orderDTO.getBuyerOpenid().equals(openId))
            throw new SellRunException(ResultEnum.PARAM_NOT_JURISDICTION_ERROR);
        OrderDTO resultDTO = service.cancel(orderDTO);
        return resultDTO == null ? ResultVOUtil.error(ResultEnum.ORDER_CANCEL_FAIL) : ResultVOUtil.success();
    }


}
