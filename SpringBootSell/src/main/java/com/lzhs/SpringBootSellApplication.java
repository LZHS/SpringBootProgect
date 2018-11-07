package com.lzhs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class SpringBootSellApplication {
    //设置时区 相差8小时
    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("CST"));
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringBootSellApplication.class, args);
    }
}
