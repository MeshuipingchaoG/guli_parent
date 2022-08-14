package com.atguigu.server_order.client;


import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(value = "server-usercenter", fallback = UCenterClientImpl.class)
public interface UCenterClient {

    //根据用户id获取用户信息
    @PostMapping("/usercenter/usermember/getUserInfo/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable("id") String id);
}
