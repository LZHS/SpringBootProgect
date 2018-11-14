package com.lzhs.enums;

import lombok.Getter;

import java.security.PrivateKey;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/14 : 10:37 AM<br/>
 */
@Getter
public enum OrderStatusEnum {
    NEW(0, "新订单"), FINISH(1, "完结订单"), CANCEL(2, "被取消订单");

    private Integer code;

    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
