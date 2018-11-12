package com.lzhs.services.impls;

import com.lzhs.data_object.ProductCategory;
import com.lzhs.data_object.ProductInfo;
import com.lzhs.repository.ProductInfoRepository;
import com.lzhs.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/8 : 3:08 PM<br/>
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        Optional<ProductInfo> result = repository.findById(productId);
        return result.isPresent() ? result.get() : null;
    }

    @Override
    public List<ProductInfo> findByProductStatus(Integer productStatus) {
        return repository.findByProductStatus(productStatus);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return null;
    }
}
