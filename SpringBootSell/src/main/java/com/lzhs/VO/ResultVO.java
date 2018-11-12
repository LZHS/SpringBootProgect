package com.lzhs.VO;

import lombok.Data;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/12 : 10:04 PM<br/>
 */
@Data
public class ResultVO<T> {
    /**
     * 错误码
     */
    private int code;
    /**
     * 错误提示信息
     */
    private String msg;
    /**
     * 返回具体数据信息
     */
    private  T data;
}
