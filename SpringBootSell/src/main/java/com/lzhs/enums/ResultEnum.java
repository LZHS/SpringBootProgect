package com.lzhs.enums;

import lombok.Getter;

/**
 * Description: 返回前端状态码 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/14 : 3:48 PM<br/>
 */
@Getter
public enum ResultEnum {
    /////////////   普通状态码 0 ~ 1000    ///////////////
    OK(0, "成功"),
    /////////////   产品状态码 1000 ~ 2000    ///////////////
    PRODUCT_NOT_EXIST(1001, "该商品不存在"),
    PRODUCT_STOCK_ERROR(1002, "该商品库存不足"),
    /////////////   订单状态码 2000 ~ 3000    ///////////////
    ORDER_NOT_EXIST(2001, "该订单不存在"),
    ORDER_DETAIL_NOT_EXIST(2002, "该订单详情不存在"),
    ORDER_STATUS_ERROR(2003, "该订单状态不正确"),
    ORDER_UPDATE_STATUS_FAIL(2004, "该订单状态更新失败"),
    UNKNOWN_ERROR(-1, "未知错误");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
