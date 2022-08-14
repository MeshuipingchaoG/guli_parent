package com.atguigu.server_msm.controller;


import com.atguigu.commonutils.ResultResponse;
import com.atguigu.server_msm.service.MsmService;
import com.atguigu.server_msm.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@RequestMapping("/edumsm/msm")
@RestController
@CrossOrigin
public class MsmController {

    @Autowired
    private MsmService msmService;
    
    @Autowired
    private RedisTemplate redisTemplate;

    // 发验证码到邮箱
    @GetMapping("/send/{mail}")
    public ResultResponse sendMail(@PathVariable String mail) {

        // 在redis中查找验证码
        Integer code = (Integer) redisTemplate.opsForValue().get(mail);
        if (!StringUtils.isEmpty(code)) {
            return ResultResponse.ok();
        }
        code = RandomUtils.getRandom();
      /*  HashMap<String, Integer> param = new HashMap<>();
        param.put("code", code);*/

        // 调用发送邮箱的方法
        boolean isSend = msmService.sendMail(mail, code);
        if (isSend) {
            // 设置过期时间5分钟
            redisTemplate.opsForValue().set(mail, code, 5, TimeUnit.MINUTES);
            return ResultResponse.ok();
        } else {
            return ResultResponse.error();
        }
    }
}
