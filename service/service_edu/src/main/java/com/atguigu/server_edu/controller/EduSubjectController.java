package com.atguigu.server_edu.controller;


import com.atguigu.commonutils.ResultResponse;
import com.atguigu.server_edu.entity.EduSubject;
import com.atguigu.server_edu.entity.subject.OneSubject;
import com.atguigu.server_edu.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-06-05
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    // 添加课程分类
    // 获取上传过来的文件，把文件内容读取出来

    @PostMapping("/addSubject")
    public ResultResponse addSubject(MultipartFile file) {

        subjectService.addSubject(file, subjectService);
        return ResultResponse.ok();
    }

    // 查询所有一级、二级课程分类
    @GetMapping("/getAllOneTwoSubject")
    public ResultResponse getAllOneTwoSubject() {

        List<OneSubject> list = subjectService.getAllOneTwoSubject();
        return ResultResponse.ok().data("list", list);
    }
}

