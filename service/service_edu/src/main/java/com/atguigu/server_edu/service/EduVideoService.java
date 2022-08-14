package com.atguigu.server_edu.service;

import com.atguigu.server_edu.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-06-06
 */
public interface EduVideoService extends IService<EduVideo> {

    void removeVideoByCourseId(String id);
}
