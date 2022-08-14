package com.atguigu.vod.controller;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.atguigu.commonutils.ResultResponse;
import com.atguigu.servicebase.exceptionhandler.ChenZhiWeiException;
import com.atguigu.vod.service.VodService;
import com.atguigu.vod.utils.ConstantPropertiesUtils;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.atguigu.vod.utils.InitVodClient.initVodClient;

@RestController
@RequestMapping("/eduvod/filevod")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    // 上传视频到阿里云
    @PostMapping("/uploadAliyunVideo")
    public ResultResponse uploadAliyunVideo(MultipartFile file) {

        // 返回上传视频ID
        String videoId = vodService.uploadVideo(file);
        return ResultResponse.ok().data("videoId", videoId);
    }

    // 根据视频ID删除阿里云视频
    @DeleteMapping("/removeAliyunVideo/{id}")
    public ResultResponse removeAliyunVideo(@PathVariable String id) {

        try {
            DefaultAcsClient client = initVodClient(ConstantPropertiesUtils.ACCESS_KEY_ID, ConstantPropertiesUtils.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(id);
            client.getAcsResponse(request);
            return ResultResponse.ok();
        } catch (ClientException e) {
            e.printStackTrace();
            throw new ChenZhiWeiException(20001, "删除视频失败！");
        }
    }

    // 通过多个视频Id删除多个阿里云视频
    @DeleteMapping("/deleteManyVideo")
    public ResultResponse deleteManyVideo(@RequestParam("videoIdList") List<String> videoIdList) {

        vodService.deleteManyVideo(videoIdList);
        return ResultResponse.ok();

    }

    // 根据视频Id获得视频播放凭证
    @GetMapping("/getPlayAuto/{videoId}")
    public ResultResponse getPlayAutoById(@PathVariable String videoId) {
        try {
            DefaultAcsClient client = initVodClient(ConstantPropertiesUtils.ACCESS_KEY_ID, ConstantPropertiesUtils.ACCESS_KEY_SECRET);
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();

            request.setVideoId(videoId);
            response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            String title = response.getVideoMeta().getTitle();
            return ResultResponse.ok().data("playAuth", playAuth).data("title", title);
        } catch (ClientException e) {
            e.printStackTrace();
            throw new ChenZhiWeiException(20001, "获取凭证失败！");
        }

    }

}
