package com.lzhs.repository;

import com.lzhs.data_object.OrderMaster;
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

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/14 : 2:11 PM<br/>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasterRepositoryTest {
    @Autowired
    OrderMasterRepository repository;

    @Test
    public void findByBuyerOpenid() {
        PageRequest pageRequest = PageRequest.of(0, 20);
        Page<OrderMaster> masterPage = repository.findByBuyerOpenid("1234abcd", pageRequest);
        Assert.assertNotEquals(masterPage.getTotalElements(),0);
    }

    @Test
    @Transactional
    public void save() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("LZHS");
        orderMaster.setBuyerIphone("12345678901");
        orderMaster.setBuyerAddress("成都市高新区天府四街");
        orderMaster.setBuyerOpenid("1234abcd");
        orderMaster.setOrderAmount(new BigDecimal(22.5));
        OrderMaster result = repository.save(orderMaster);
        log.info(result.toString());
        Assert.assertNotNull(result);
    }
}