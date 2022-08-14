package com.atguigu.server_edu.client;

import com.atguigu.commonutils.ResultResponse;
import com.atguigu.server_usercenter.entity.UcenterMember;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UCenterClientImpl implements UCenterClient {

    @Override
    public UcenterMember getUserInfoOrder(String id) {
        return null;
    }
}
