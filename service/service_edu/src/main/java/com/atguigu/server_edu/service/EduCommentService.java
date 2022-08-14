package com.atguigu.server_edu.service;

import com.atguigu.server_edu.entity.EduComment;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-07-29
 */
public interface EduCommentService extends IService<EduComment> {

    void addComment(EduComment eduComment, HttpServletRequest request);
}
