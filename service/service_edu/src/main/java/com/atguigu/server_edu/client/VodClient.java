package com.atguigu.server_edu.client;


import com.atguigu.commonutils.ResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(name = "service-vod", fallback = VodClientImpl.class)  // 调用的服务的名称
public interface VodClient {

    // 根据视频ID删除阿里云视频
    // 一定要写全路径
    @DeleteMapping("/eduvod/filevod/removeAliyunVideo/{id}")
    public ResultResponse removeAliyunVideo(@PathVariable("id") String id);

    // 通过多个视频Id删除多个阿里云视频
    @DeleteMapping("/eduvod/filevod/deleteManyVideo")
    public ResultResponse deleteManyVideo(@RequestParam("videoIdList") List<String> videoIdList);
}
