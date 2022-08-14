package com.atguigu.server_usercenter.controller;


import com.atguigu.server_usercenter.utils.ConstantWxUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;

@Controller
@CrossOrigin
@RequestMapping("/api/ucenter/wx")
public class WxApiController {

    @GetMapping("/login")
    public String loginByWx() {

        // 微信开放平台授权baseUrl
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
        "?appid=%s" +
        "&redirect_uri=%s" +
        "&response_type=code" +
        "&scope=snsapi_login" +
        "&state=%s" +
        "#wechat_redirect";

        // 对redirect_url 进行URLEncoder编码
        String redirectUrl = ConstantWxUtils.WX_OPEN_APP_REDIRECT_URL;
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 设置%s里的值
        String url = String.format(
                baseUrl,
                ConstantWxUtils.WX_OPEN_APP_ID,
                redirectUrl,
                "atguigu"
        );
        return "redirect:" + url;
    }
}
