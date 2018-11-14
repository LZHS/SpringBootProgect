package com.lzhs.enums;

import lombok.Getter;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/14 : 10:37 AM<br/>
 */
@Getter
public enum PayStatusEnum {
    WAIT(0, "等待支付"), SUCCESS(1, "支付完成"), FAIL(2, "支付失败");

    private Integer code;

    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
