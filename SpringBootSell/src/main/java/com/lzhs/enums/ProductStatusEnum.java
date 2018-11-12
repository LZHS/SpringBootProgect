package com.lzhs.enums;

import lombok.Getter;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/8 : 3:13 PM<br/>
 */
@Getter
public enum ProductStatusEnum {
    UP(0, "上架"), DOWN(1, "下架");
    private Integer code;
    private String msg;

    ProductStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
