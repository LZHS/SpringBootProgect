package com.lzhs.repository;

import com.lzhs.data_object.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/8 : 2:44 PM<br/>
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
    /**
     * 查询商品状态
     *
     * @param productStatu
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer productStatu);

}
