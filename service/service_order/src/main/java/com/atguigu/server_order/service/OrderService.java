package com.atguigu.server_order.service;

import com.atguigu.server_order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-07-30
 */
public interface OrderService extends IService<Order> {

    String createOrder(String courseId, String memberId);
}
