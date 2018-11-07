package com.lzhs.repository;

import com.lzhs.data_object.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/7 : 6:19 PM<br/>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findAll() {
        List<ProductCategory> mDatas = repository.findAll();
        log.info("查询到的数组大小 ： {}", mDatas == null ? 0 : mDatas.size());
        for (int i = 0; i < mDatas.size(); i++) log.info("下标 「{}」 , 值 = {}", i, mDatas.get(i).toString());
    }

    @Test
    @Transactional
    public void saveTest() {
        Optional<ProductCategory> optional = repository.findById(2);
        if (optional.isPresent()) {
            ProductCategory category = optional.get();
            category.setCategoryName("真的是帅爆了呀abcd");
            category.setCategoryType(27);
            ProductCategory result = repository.save(category);
            Assert.assertNotNull(result);
        }
        findAll();

    }

    @Test
    @Transient
    public void findByCategoryTypeIn() {
        List<Integer> categoryTypeList = Arrays.asList(18, 27, 3);
        List<ProductCategory> mDatas = repository.findByCategoryTypeIn(categoryTypeList);
        Assert.assertNotEquals(0, mDatas.size());
    }


}