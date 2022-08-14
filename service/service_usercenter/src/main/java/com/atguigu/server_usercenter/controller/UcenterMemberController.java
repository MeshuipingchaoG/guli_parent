package com.atguigu.server_usercenter.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.ResultResponse;
import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import com.atguigu.server_usercenter.entity.UcenterMember;
import com.atguigu.server_usercenter.entity.vo.RegisterVo;
import com.atguigu.server_usercenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-07-22
 */
@RestController
@CrossOrigin
@RequestMapping("/usercenter/usermember")
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    @PostMapping("/login")
    public ResultResponse loginUser(@RequestBody UcenterMember ucenterMember) {
        // 传入用户
        // 调用登录方法，返回token
        String token = memberService.login(ucenterMember);
        return ResultResponse.ok().data("token", token);
    }

    // 注册用户
    @PostMapping("/register")
    public ResultResponse userRegister(@RequestBody RegisterVo registerVo) {

        memberService.register(registerVo);
        return ResultResponse.ok().msg("注册成功！");
    }

    // 通过token查用户id
    @GetMapping("/getUserInfo")
    public ResultResponse getUserInfo(HttpServletRequest request) {

        String userId = JwtUtils.getMemberIdByJwtToken(request);
        // 通过id查用户信息
        UcenterMember user = memberService.getById(userId);
        return ResultResponse.ok().data("user", user);
    }

    //根据用户id获取用户信息
    @PostMapping("/getUserInfo/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable String id) {
        UcenterMember member = memberService.getById(id);
        //把member对象里面值复制给UcenterMember对象
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(member, ucenterMemberOrder);
        return ucenterMemberOrder;
    }



    /**
     * @description: 查寻某天注册的人数
     * @author: chenzhiwei
     * @date: 2022/8/3 23:27
     * @param: [day]
     * @return: com.atguigu.commonutils.ResultResponse
     **/
    @GetMapping("/getRegisterCount/{day}")
    public ResultResponse getRegisterCount(@PathVariable String day) {

        int count = memberService.selectResisterCount(day);
        return ResultResponse.ok().data("count", count);
    }

}

