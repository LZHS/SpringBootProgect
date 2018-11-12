package com.lzhs.services;

import com.lzhs.data_object.ProductCategory;
import com.lzhs.data_object.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/8 : 3:02 PM<br/>
 */
public interface ProductService {


    ProductInfo findOne(String productId);

    /**
     * 通过商品上架状态来查询数据
     *
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);

    /**
     * 查询所有商品
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    // TODO: 2018/11/8   1.添加库存  2.减少库存


}
