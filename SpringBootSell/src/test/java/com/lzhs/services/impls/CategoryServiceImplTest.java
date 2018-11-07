package com.lzhs.services.impls;

import com.lzhs.data_object.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;


/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/8 : 1:24 AM<br/>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {


    @Autowired
    CategoryServiceImpl service;

    @Test
    public void findOne() {
        ProductCategory category = service.findOne(1);
        Assert.assertNotNull(category);
    }

    @Test
    public void findAll() {
        List<ProductCategory> resulr = service.findAll();
        Assert.assertNotEquals(0, resulr.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> resulr = service.findByCategoryTypeIn(Arrays.asList(19,27,3));
        Assert.assertNotEquals(0, resulr.size());
    }

    @Test
    @Transactional
    public void save() {
        ProductCategory category = new ProductCategory();
        category.setCategoryType(13);
        category.setCategoryName("啦啦德玛西亚abcd");
        ProductCategory result = service.save(category);
        Assert.assertNotNull(result);

    }
}