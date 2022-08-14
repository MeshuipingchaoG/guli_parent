package com.atguigu.server_edu.service.impl;

import com.atguigu.server_edu.client.VodClient;
import com.atguigu.server_edu.entity.EduVideo;
import com.atguigu.server_edu.mapper.EduVideoMapper;
import com.atguigu.server_edu.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-06-06
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    @Override
    public void removeVideoByCourseId(String id) {
        /**
         * 1 根据课程ID查到视频id
         * 2 调用删除多个视频的方法
         */
        QueryWrapper<EduVideo> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("course_id",id);
        videoWrapper.select("id","video_source_id");
        // 查到的所有videoId集合
        List<EduVideo> videoList = baseMapper.selectList(videoWrapper);

        // List<EduVideo> 变成 List<String>
        List<String> videoIds = new ArrayList<>();
        for (int i = 0; i < videoList.size(); i++) {
            EduVideo eduVideo = videoList.get(i);
            String videoSourceId = eduVideo.getVideoSourceId();
            if (!StringUtils.isEmpty(videoSourceId)) {
                // 放到videoIds集合里面
                videoIds.add(videoSourceId);
            }
        }

        // 调用vod中的删除多个视频方法
        if (videoIds.size() > 0) {
            vodClient.deleteManyVideo(videoIds);
        }

        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", id);
        baseMapper.delete(wrapper);
    }
}
