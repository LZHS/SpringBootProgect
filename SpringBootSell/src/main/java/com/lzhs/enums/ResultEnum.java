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
    /////////////   普通状态码 0 ~ 500    ///////////////
    OK(0, "成功"),
    PARAM_ERROR(1, "参数错误"),
    /////////////   购物车相关信息 500 ~ 1000    ///////////////
    PARAM_CART_ERROR(501, "购物车信息为空"),
    PARAM_NOT_JURISDICTION_ERROR(502, "当前用户无权操作该订单"),
    /////////////   产品状态码 1000 ~ 1500    ///////////////
    PRODUCT_NOT_EXIST(1001, "该商品不存在"),
    PRODUCT_STOCK_ERROR(1002, "该商品库存不足"),
    /////////////   订单状态码 1500 ~ 2000    ///////////////
    ORDER_NOT_EXIST(1501, "该订单不存在"),
    ORDER_DETAIL_NOT_EXIST(1502, "该订单详情不存在"),
    ORDER_STATUS_ERROR(1503, "该订单状态不正确"),
    ORDER_UPDATE_STATUS_FAIL(1504, "该订单状态更新失败"),
    ORDER_PAY_STATUS_ERROR(1505, "该订单支付状态不正确"),
    ORDER_PAY_UPDATE_STATUS_FAIL(1506, "该订单支付状态更新失败"),
    ORDER_CANCEL_FAIL(1507, "该订单取消失败"),
    /////////////   微信相关错误 2000 ~ 2500    ///////////////
    WECHAT_MP_OAUTH_ERROR(2001, "获取微信授权失败"),
    UNKNOWN_ERROR(-1, "未知错误");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
