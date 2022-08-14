package com.atguigu.server_edu.service.impl;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.ResultResponse;
import com.atguigu.server_edu.client.UCenterClient;
import com.atguigu.server_edu.entity.EduComment;
import com.atguigu.server_edu.mapper.EduCommentMapper;
import com.atguigu.server_edu.service.EduCommentService;
import com.atguigu.server_usercenter.entity.UcenterMember;
import com.atguigu.servicebase.exceptionhandler.ChenZhiWeiException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-07-29
 */
@Service
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements EduCommentService {



    @Override
    public void addComment(EduComment eduComment, HttpServletRequest request) {


    }
}
