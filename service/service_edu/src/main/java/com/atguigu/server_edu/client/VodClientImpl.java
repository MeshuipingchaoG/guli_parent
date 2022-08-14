package com.atguigu.server_edu.client;

import com.atguigu.commonutils.ResultResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodClientImpl implements VodClient{
    @Override
    public ResultResponse removeAliyunVideo(String id) {
        return ResultResponse.error().msg("删除视频出错了!");
    }

    @Override
    public ResultResponse deleteManyVideo(List<String> videoIdList) {
        return ResultResponse.error().msg("删除多个视频出错了！");
    }
}
