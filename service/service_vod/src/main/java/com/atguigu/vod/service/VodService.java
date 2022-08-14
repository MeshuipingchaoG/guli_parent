package com.atguigu.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {
    // 上传视频到vod
    String uploadVideo(MultipartFile file);

    void deleteManyVideo(List videoIdList);
}
