package com.atguigu.server_order.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.ResultResponse;
import com.atguigu.server_order.entity.Order;
import com.atguigu.server_order.service.OrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-07-30
 */
@RestController
@RequestMapping("/serviceorder/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 通过课程id创建订单
    @PostMapping("/createOrder/{courseId}")
    public ResultResponse createOrder(@PathVariable String courseId, HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        // 创建订单，返回单号
        String orderNo = orderService.createOrder(courseId, memberId);
        return ResultResponse.ok().data("orderNo", orderNo);
    }

    // 根据订单号查询订单信息
    @GetMapping("/getOrderInfoById/{orderNo}")
    public ResultResponse getOrderInfoById(@PathVariable String orderNo) {

        QueryWrapper<Order> wrapperOrder = new QueryWrapper<>();
        wrapperOrder.eq("order_no", orderNo);
        Order order = orderService.getOne(wrapperOrder);
        return ResultResponse.ok().data("order", order);
    }

}

