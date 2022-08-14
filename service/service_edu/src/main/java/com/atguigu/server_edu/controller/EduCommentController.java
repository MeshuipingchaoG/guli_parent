package com.atguigu.server_edu.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.ResultResponse;
import com.atguigu.server_edu.client.UCenterClient;
import com.atguigu.server_edu.entity.EduComment;
import com.atguigu.server_edu.service.EduCommentService;
import com.atguigu.server_usercenter.entity.UcenterMember;
import com.atguigu.servicebase.exceptionhandler.ChenZhiWeiException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-07-29
 */
@RestController
@RequestMapping("/eduservice/comment")
@CrossOrigin
public class EduCommentController {

    @Autowired
    private EduCommentService commentService;

    @Autowired
    private UCenterClient uCenterClient;

    // 分页查询
    @GetMapping("/getAllComment/{current}/{limit}")
    public ResultResponse getAllComment(@PathVariable long current, @PathVariable long limit) {

        Page<EduComment> pageComment = new Page<>(current, limit);

        commentService.page(pageComment, null);
        List<EduComment> records = pageComment.getRecords();
        long total = pageComment.getTotal();
        long size = pageComment.getSize();
        return ResultResponse.ok().data("list", records).data("total", total).data("size", size);
    }

    // 添加评论
    @PostMapping("/addComment")
    public ResultResponse addComment(@RequestBody EduComment eduComment, HttpServletRequest request) {

        // 用户id
        String id = JwtUtils.getMemberIdByJwtToken(request);
        if (StringUtils.isEmpty(id)) {
            throw new ChenZhiWeiException(20001, "请先登录！");
        }
        eduComment.setMemberId(id);
        UcenterMember user = uCenterClient.getUserInfoOrder(id);
        eduComment.setNickname(user.getNickname());
        eduComment.setAvatar(user.getAvatar());
        commentService.save(eduComment);
        return ResultResponse.ok();
    }
}

