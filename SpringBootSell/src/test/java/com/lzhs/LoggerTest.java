package com.lzhs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/7 : 2:28 PM<br/>
 */
@RunWith(SpringRunner.class)
@Slf4j
public class LoggerTest {

    @Test
    public void test1() {
        String name = "LZHS";
        int  age = 27;
        log.debug("=================================");
        log.info("info name = {}, age = {}",name,age);
        log.error("=================================");

    }
}
