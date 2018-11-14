package com.lzhs.exceptions;

import com.lzhs.enums.ResultEnum;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/14 : 3:47 PM<br/>
 */
public class SellRunException extends RuntimeException {
    private int code;
    public SellRunException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

}
