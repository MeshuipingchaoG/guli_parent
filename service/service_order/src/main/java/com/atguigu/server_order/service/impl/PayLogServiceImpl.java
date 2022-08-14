package com.atguigu.server_order.service.impl;

import com.atguigu.server_order.entity.PayLog;
import com.atguigu.server_order.mapper.PayLogMapper;
import com.atguigu.server_order.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-07-30
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

}
