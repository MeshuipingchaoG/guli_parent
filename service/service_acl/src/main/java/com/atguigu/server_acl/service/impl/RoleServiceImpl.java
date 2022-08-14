package com.atguigu.server_acl.service.impl;

import com.atguigu.server_acl.entity.Role;
import com.atguigu.server_acl.mapper.RoleMapper;
import com.atguigu.server_acl.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenzhiwei
 * @since 2022-08-09
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
