package com.lzhs.utils;

import com.lzhs.VO.ResultVO;
import com.lzhs.enums.ResultEnum;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/13 : 12:50 AM<br/>
 */
public class ResultVOUtil {
    public static ResultVO success(Object result) {
        ResultVO resultVO = error(0, "成功");
        resultVO.setData(result);
        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }

    public static ResultVO error(ResultEnum resultEnum) {
        return error(resultEnum.getCode(), resultEnum.getMsg());
    }
}
