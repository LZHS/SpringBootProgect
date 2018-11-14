package com.lzhs.utils;

import java.util.UUID;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/14 : 4:31 PM<br/>
 */
public class KeyUtil {
    /**
     * 生成 唯一的主键
     *
     * @return
     */
    public static synchronized String genUniqueKey() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr = str.replace("-", "");
        return uuidStr.toLowerCase();
    }

    public static void main(String... args) {
        for (int i = 0; i < 13; i++) System.out.println(genUniqueKey());
    }
}
