package com.lzhs.services.impls;

import com.lzhs.data_object.ProductInfo;
import com.lzhs.enums.ProductStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/8 : 3:17 PM<br/>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductServiceImplTest {
    @Autowired
    ProductServiceImpl productService;

    @Test
    public void findOne() {
        ProductInfo resule = productService.findOne("223");
        Assert.assertEquals(null, resule);
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> productStatus = productService.findByProductStatus(ProductStatusEnum.UP.getCode());
        Assert.assertNotEquals(0, productStatus);
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = PageRequest.of(0, 2);
       Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
       log.info("getTotalElements = {} ",productInfoPage.getTotalElements());
    }

    @Test
    public void save() {
    }
}