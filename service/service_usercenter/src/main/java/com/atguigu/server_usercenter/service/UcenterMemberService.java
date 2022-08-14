package com.atguigu.server_usercenter.service;

import com.atguigu.server_usercenter.entity.UcenterMember;
import com.atguigu.server_usercenter.entity.vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-07-22
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember ucenterMember);

    void register(RegisterVo registerVo);

    int selectResisterCount(String day);
}
