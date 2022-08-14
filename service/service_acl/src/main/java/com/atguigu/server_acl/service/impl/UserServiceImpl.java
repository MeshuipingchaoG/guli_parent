package com.atguigu.server_acl.service.impl;

import com.atguigu.server_acl.entity.User;
import com.atguigu.server_acl.mapper.UserMapper;
import com.atguigu.server_acl.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author chenzhiwei
 * @since 2022-08-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
