package com.lzhs.controllers;

import com.lzhs.enums.ResultEnum;
import com.lzhs.exceptions.SellRunException;
import com.lzhs.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/16 : 4:34 PM<br/>
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WeChatController {
    @Autowired
    WxMpService wxMpService;
    static String STATE = "";

    @GetMapping("/test/auth")
    public void auth(@RequestParam("code") String code,
                     @RequestParam("state") String state) {
        log.info("【进入auth 方法】");
        log.info("code = {} ,state = {}", code, state);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=wx68ab0fd5d2f2332a" +
                "&secret=667d7bd47c93df6e238f2c92fc6bcbca" +
                "&code=" + code +
                "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        log.info("【微信请求- 返回结果】 {}", result);
    }


    @GetMapping("/authorize")
    public String authorize() {
        STATE = KeyUtil.genUniqueKey();
        String url = "http://lzhs.natapp1.cc/SpringBootSell/wechat/userInfo";

        String redirectUrl =
                wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, STATE);
        log.info("【微信网页授权】获取code result = {}", redirectUrl);
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String state) {
        if (state.equals(STATE)) new SellRunException(ResultEnum.WECHAT_MP_OAUTH_ERROR);
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = null;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new SellRunException(e.getError().getErrorCode(), e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        String redirectUrl = "";
        return "redirect:" + redirectUrl + "?openId=" + openId;
    }
}
