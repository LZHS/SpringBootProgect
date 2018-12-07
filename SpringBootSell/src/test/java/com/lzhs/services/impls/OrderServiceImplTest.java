package com.lzhs.services.impls;

import com.lzhs.data_object.OrderDetail;
import com.lzhs.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/14 : 8:31 PM<br/>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    OrderServiceImpl orderService;

    final static String ORDER_ID = "6496cc6d091d42d3a50733943d919310", BUYER_OPEN_ID = "ew3euwhd7sjw9diwkq";

    @Test
    @Transactional
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("成都市高新区");
        orderDTO.setBuyerIphone("12345678901");
        orderDTO.setBuyerName("LZHS");
        orderDTO.setBuyerOpenid(BUYER_OPEN_ID);

        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId("05a47d4e3db54f3580cb7ef122b9a2ab");
        orderDetail1.setProductQuantity(2);
        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId("9bd52a8353a241b18c8b4186b90d966e");
        orderDetail2.setProductQuantity(11);
        OrderDetail orderDetail3 = new OrderDetail();
        orderDetail3.setProductId("5655553af2c84f5fa910eedcf91474cd");
        orderDetail3.setProductQuantity(5);

        orderDTO.setOrderDetailList(Arrays.asList(orderDetail2,orderDetail3));
        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建一个订单】result {}", result);
        Assert.assertNotNull(result);

    }

    @Test
    public void findById() {
        OrderDTO orderDTO = orderService.findById(ORDER_ID);
        log.info("【查询一个订单】result = {}", orderDTO.toString());
        Assert.assertNotNull(orderDTO);
    }

    @Test
    public void findByBuyerOpenid() {
        Page<OrderDTO> dtoPage = orderService.findByBuyerOpenid(BUYER_OPEN_ID, PageRequest.of(0, 20));
        log.info("【根据用户ID 查询订单】 result = {}", dtoPage.getContent());
        Assert.assertNotEquals(0, dtoPage.getTotalElements());
    }

    @Test
    public void findByBuyerAll() {
        Page<OrderDTO> dtoPage = orderService.findByBuyerAll(PageRequest.of(0, 20));
        log.info("【根据用户ID 查询订单】 result = {}", dtoPage.getContent());
        Assert.assertNotEquals(0, dtoPage.getTotalElements());
    }

    @Test
    @Transactional
    public void cancel() {
        OrderDTO orderDTO = orderService.cancel(orderService.findById(ORDER_ID));
        log.info("【取消订单】 result = {}", orderDTO);
        Assert.assertNotNull(orderDTO);
    }

    @Test
    @Transactional
    public void finish() {
        OrderDTO orderDTO = orderService.cancel(orderService.findById(ORDER_ID));
        log.info("【完结订单】 result = {}", orderDTO);
        Assert.assertNotNull(orderDTO);

    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.cancel(orderService.findById(ORDER_ID));
        log.info("【支付订单】 result = {}", orderDTO);
        Assert.assertNotNull(orderDTO);
    }
}