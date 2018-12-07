package com.lzhs.exceptions;

import com.lzhs.VO.ResultVO;
import com.lzhs.enums.ResultEnum;
import com.lzhs.utils.ResultVOUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/29 : 3:04 PM<br/>
 */
public class ExceptionHandle {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultVO handle(Exception ex) {
        if (ex instanceof SellRunException) {
            SellRunException sellErr = (SellRunException) ex;
            return ResultVOUtil.error(sellErr.getCode(), sellErr.getMessage());
        }else return ResultVOUtil.error(ResultEnum.UNKNOWN_ERROR);
    }
}
