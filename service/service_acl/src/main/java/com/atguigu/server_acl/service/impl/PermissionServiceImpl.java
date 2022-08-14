package com.atguigu.server_acl.service.impl;

import com.atguigu.server_acl.entity.Permission;
import com.atguigu.server_acl.mapper.PermissionMapper;
import com.atguigu.server_acl.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author chenzhiwei
 * @since 2022-08-09
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
