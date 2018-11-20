package com.lzhs.controllers;

import com.lzhs.VO.ProductInfoVO;
import com.lzhs.VO.ProductVO;
import com.lzhs.VO.ResultVO;
import com.lzhs.data_object.ProductInfo;
import com.lzhs.enums.ProductStatusEnum;
import com.lzhs.services.CategoryService;
import com.lzhs.services.ProductService;
import com.lzhs.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:  返回买家相关API <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/12 : 10:01 PM<br/>
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;


    @GetMapping("/list")
    public ResultVO list() {
        List<ProductInfo> productInfoList = productService.findByProductStatus(ProductStatusEnum.UP.getCode());
        List<Integer> categoryTypeList = productInfoList
                .stream()
                .map(mapper -> mapper.getCategoryType())
                .collect(Collectors.toList());
        List<ProductVO> productVOS = categoryService.findByCategoryTypeIn(categoryTypeList)
                .stream()
                .map(categoryType -> {
                    ProductVO productVO = new ProductVO();
                    productVO.setCategoryName(categoryType.getCategoryName());
                    productVO.setCategoryType(categoryType.getCategoryType());
                    productVO.setProductInfoVOList(productInfoList.stream()
                            .filter(productInfo -> productInfo.getCategoryType() == categoryType.getCategoryType())
                            .map(productInfo -> {
                                ProductInfoVO productInfoVO = new ProductInfoVO();
                                BeanUtils.copyProperties(productInfo, productInfoVO);
                                return productInfoVO;
                            }).collect(Collectors.toList()));
                    return productVO;
                }).collect(Collectors.toList());
        return ResultVOUtil.success(productVOS);
    }

}
