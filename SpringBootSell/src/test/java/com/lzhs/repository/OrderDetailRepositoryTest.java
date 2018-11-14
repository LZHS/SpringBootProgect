package com.lzhs.repository;

import com.lzhs.data_object.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/14 : 2:52 PM<br/>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderDetailRepositoryTest {

    @Autowired
    OrderDetailRepository repository;

    @Test
    @Transactional
    public void save() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("abcd1234");
        orderDetail.setOrderId("123456");
        orderDetail.setProductId("a1b2c3d4");
        orderDetail.setProductName("皮蛋瘦肉粥");
        orderDetail.setProductIcon("http://xxxx.png");
        orderDetail.setProductPrice(new BigDecimal(3.5));
        orderDetail.setProductQuantity(35);
        OrderDetail detail = repository.save(orderDetail);
        Assert.assertNotNull(detail);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> detailList = repository.findByOrderId("123456");
        detailList.stream().forEach(detail -> log.info(detail.toString()));
        Assert.assertEquals(0, detailList.size());
    }


}