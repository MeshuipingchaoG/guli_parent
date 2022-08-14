package com.atguigu.server_edu.client;

import com.atguigu.server_usercenter.entity.UcenterMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Component
@FeignClient(name = "server-usercenter", fallback = UCenterClientImpl.class)
public interface UCenterClient {

    @PostMapping("/usercenter/usermember/getUserInfo/{id}")
    public UcenterMember getUserInfoOrder(@PathVariable("id") String id);
}
