package com.lzhs.repository;

import com.lzhs.data_object.ProductCategory;
import com.lzhs.data_object.ProductInfo;
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

import static org.junit.Assert.*;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/8 : 2:48 PM<br/>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductInfoRepositoryTest {
    @Autowired
    ProductInfoRepository repository;

    @Test
    public void findByProductStatus() {
        List<ProductInfo> result=repository.findByProductStatus(0);
        Assert.assertNotEquals(0,result.size());
    }

    @Test
    @Transactional
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("12312412");
        productInfo.setProdcutName("皮蛋瘦肉粥");
        productInfo.setProductPrice(new BigDecimal(23.5));
        productInfo.setProductStock(128);
        productInfo.setProductDescription("养生壮阳的粥");
        productInfo.setProductIcon("http://xxxxx.png");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(27);
        ProductInfo result=repository.save(productInfo);
        Assert.assertNotNull(result);
        findAll();

    }

    @Test
    public void findAll() {
        List<ProductInfo> mDatas = repository.findAll();
        log.info("查询到的数组大小 ： {}", mDatas == null ? 0 : mDatas.size());
        for (int i = 0; i < mDatas.size(); i++) log.info("下标 「{}」 , 值 = {}", i, mDatas.get(i).toString());
    }
}