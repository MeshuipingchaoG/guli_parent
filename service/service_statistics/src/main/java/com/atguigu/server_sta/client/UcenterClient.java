package com.atguigu.server_sta.client;

import com.atguigu.commonutils.ResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "server-usercenter", fallback = UcenterClientImpl.class)
public interface UcenterClient {

    @GetMapping("/usercenter/usermember/getRegisterCount/{day}")
    public ResultResponse getRegisterCount(@PathVariable("day") String day);
}
