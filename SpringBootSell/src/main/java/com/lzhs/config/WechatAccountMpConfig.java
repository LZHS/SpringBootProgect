package com.lzhs.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/19 : 4:33 PM<br/>
 */
@Component
@Data
@ConfigurationProperties("wechat")
public class WechatAccountMpConfig {
    private String appId;
    private String appSecret;
}
