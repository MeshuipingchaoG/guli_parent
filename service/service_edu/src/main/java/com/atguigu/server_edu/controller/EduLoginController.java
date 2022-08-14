package com.atguigu.server_edu.controller;

import com.atguigu.commonutils.ResultResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {

    // login
    @PostMapping("/login")
    public ResultResponse login() {

        return ResultResponse.ok().data("token", "admin");
    }

    // info
    @GetMapping("/info")
    public ResultResponse info() {

        return ResultResponse.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "https://chenzhiwei-edu.oss-cn-beijing.aliyuncs.com/%E5%A3%81%E7%BA%B8/%E7%AE%A1%E7%90%86%E5%91%98.jpeg");
    }
}
