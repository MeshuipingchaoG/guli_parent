package com.atguigu.server_order.service.impl;

import com.atguigu.commonutils.ordervo.CourseDetailsOrder;
import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import com.atguigu.server_order.client.EduClient;
import com.atguigu.server_order.client.UCenterClient;
import com.atguigu.server_order.entity.Order;
import com.atguigu.server_order.mapper.OrderMapper;
import com.atguigu.server_order.service.OrderService;
import com.atguigu.server_order.utils.OrderNoUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-07-30
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private EduClient eduClient;

    @Autowired
    private UCenterClient uCenterClient;

    @Override
    public String createOrder(String courseId, String memberId) {

        // 1 调用edu模块中的查询课程信息方法
        CourseDetailsOrder courseInfo = eduClient.getCourseInfo(courseId);

        // 2 调用usercenter模块中的查询用户信息的方法
        UcenterMemberOrder userInfoOrder = uCenterClient.getUserInfoOrder(memberId);

        // 创建订单
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseInfo.getTitle());
        order.setCourseCover(courseInfo.getCover());
        order.setTeacherName(courseInfo.getName());
        order.setMemberId(memberId);
        order.setNickname(userInfoOrder.getNickname());
        order.setMemberAvatar(userInfoOrder.getAvatar());
        order.setMail(userInfoOrder.getMail());
        order.setTotalFee(courseInfo.getPrice());
        order.setPayType(1);
        order.setStatus(0);
        // 将数据加入到数据库
        baseMapper.insert(order);
        return order.getOrderNo();
    }
}
