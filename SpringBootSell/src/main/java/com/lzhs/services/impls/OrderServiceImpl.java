package com.lzhs.services.impls;

import com.lzhs.converters.OrderMaster2OrderDTOConverter;
import com.lzhs.data_object.OrderDetail;
import com.lzhs.data_object.OrderMaster;
import com.lzhs.data_object.ProductInfo;
import com.lzhs.dto.CartDTO;
import com.lzhs.dto.OrderDTO;
import com.lzhs.enums.OrderStatusEnum;
import com.lzhs.enums.PayStatusEnum;
import com.lzhs.enums.ResultEnum;
import com.lzhs.exceptions.SellRunException;
import com.lzhs.repository.OrderDetailRepository;
import com.lzhs.repository.OrderMasterRepository;
import com.lzhs.services.OrderService;
import com.lzhs.services.ProductService;
import com.lzhs.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/14 : 3:27 PM<br/>
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    ProductService productService;
    @Autowired
    OrderDetailRepository detailRepository;

    @Autowired
    OrderMasterRepository masterRepository;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        List<CartDTO> cartDTOS = new ArrayList<>();
        //1。查询数据库商品,计算总价格
        BigDecimal orderAmount = orderDTO.getOrderDetailList()
                .stream()
                .map(orderDetail -> {
                    ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    orderDetail.setOrderId(orderId);
                    orderDetailList.add(orderDetail);
                    cartDTOS.add(new CartDTO(productInfo.getProductId(), orderDetail.getProductQuantity()));
                    return orderDetail.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        //3。写入订单数据库（OrderDetail、OrderMaster）
        detailRepository.saveAll(orderDetailList);
        orderDTO.setOrderId(orderId);
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        masterRepository.save(orderMaster);
        //4。 如果下单成功 更改数据库库存
        productService.decreaseStock(cartDTOS);
        return orderDTO;
    }

    @Override
    public OrderDTO findById(String orderId) {
        Optional<OrderMaster> orderMaster = masterRepository.findById(orderId);
        if (!orderMaster.isPresent()) throw new SellRunException(ResultEnum.ORDER_NOT_EXIST);
        List<OrderDetail> orderDetails = detailRepository.findByOrderId(orderMaster.get().getOrderId());
        if (orderDetails.size() == 0) throw new SellRunException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster.get(), orderDTO);
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findByBuyerOpenid(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> masterPage = masterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDTO> dtoList = OrderMaster2OrderDTOConverter.convert(masterPage.getContent());
        return new PageImpl<>(dtoList, pageable, masterPage.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        log.info("【取消订单】{}", orderDTO);
        //1.判断订单状态是否存在并且是否为新订单状态
        OrderMaster orderMaster = checkOrder(orderDTO);
        //2.修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster saveResult = masterRepository.save(orderMaster);
        if (saveResult == null) throw new SellRunException(ResultEnum.ORDER_UPDATE_STATUS_FAIL);
        //3.返回库存
        productService.increaseStock(orderDTO.getOrderDetailList().stream()
                .map(orderDetail -> new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity()))
                .collect(Collectors.toList()));
        //4.如果用户已支付，需要退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS)) {
            // TODO: 2018/11/14 由于还没有对支付功能进行开发，所以该功能点暂时搁置
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        log.info("【完结订单信息】{}", orderDTO);
        // 判断订单状态
        OrderMaster orderMaster = checkOrder(orderDTO);
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        orderMaster.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        OrderMaster saveResult = masterRepository.save(orderMaster);
        if (saveResult == null) throw new SellRunException(ResultEnum.ORDER_UPDATE_STATUS_FAIL);
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        log.info("【支付订单】{}", orderDTO);
        // 判断订单状态
        OrderMaster orderMaster = checkOrder(orderDTO);
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT))
            throw new SellRunException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster saveResult = masterRepository.save(orderMaster);
        if (saveResult == null) throw new SellRunException(ResultEnum.ORDER_PAY_UPDATE_STATUS_FAIL);
        return orderDTO;
    }

    private OrderMaster checkOrder(OrderDTO orderDTO) {
        Optional<OrderMaster> orderMaster = masterRepository.findById(orderDTO.getOrderId());
        if (!orderMaster.isPresent()) throw new SellRunException(ResultEnum.ORDER_NOT_EXIST);
        List<OrderDetail> orderDetails = detailRepository.findByOrderId(orderMaster.get().getOrderId());
        if (orderDetails.size() == 0) throw new SellRunException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode()))
            throw new SellRunException(ResultEnum.ORDER_STATUS_ERROR);
        return orderMaster.get();
    }

}
