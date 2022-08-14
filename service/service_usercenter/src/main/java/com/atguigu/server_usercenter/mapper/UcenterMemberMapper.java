package com.atguigu.server_usercenter.mapper;

import com.atguigu.server_usercenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-07-22
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    int getResisterCount(String day);
}
