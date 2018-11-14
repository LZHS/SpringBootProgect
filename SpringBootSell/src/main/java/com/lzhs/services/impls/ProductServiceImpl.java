package com.lzhs.services.impls;

import com.lzhs.data_object.ProductInfo;
import com.lzhs.dto.CartDTO;
import com.lzhs.enums.ResultEnum;
import com.lzhs.exceptions.SellRunException;
import com.lzhs.repository.ProductInfoRepository;
import com.lzhs.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/8 : 3:08 PM<br/>
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        Optional<ProductInfo> result = repository.findById(productId);
        if (!result.isPresent()) {
            throw new SellRunException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        return result.get();
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
        return repository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOS) {
        List<ProductInfo> result = repository.saveAll(cartDTOS.stream().map(cartDTO -> {
            ProductInfo productInfo = findOne(cartDTO.getProductId());
            productInfo.setProductStock(productInfo.getProductStock() + cartDTO.getProductQuantity());
            return productInfo;
        }).collect(Collectors.toList()));
        log.info("【添加库存】 result = {}", result);
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOS) {
        List<ProductInfo> productInfoList = new ArrayList<>();
        cartDTOS.stream().forEach(cartDTO -> {
            ProductInfo productInfo = findOne(cartDTO.getProductId());
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0)
                throw new SellRunException(ResultEnum.PRODUCT_STOCK_ERROR);
            productInfo.setProductStock(result);
            productInfoList.add(productInfo);
        });
        repository.saveAll(productInfoList);
    }
}
