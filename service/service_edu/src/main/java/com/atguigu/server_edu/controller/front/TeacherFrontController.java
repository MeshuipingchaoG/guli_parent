package com.atguigu.server_edu.controller.front;


import com.atguigu.commonutils.ResultResponse;
import com.atguigu.server_edu.entity.EduCourse;
import com.atguigu.server_edu.entity.EduTeacher;
import com.atguigu.server_edu.service.EduCourseService;
import com.atguigu.server_edu.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/eduservice/teacherfront")
public class TeacherFrontController {

    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;

    // 前端教师查询、分页
    @GetMapping("/getTeacher/{current}/{limit}")
    public ResultResponse getTeacher(@PathVariable long current, @PathVariable long limit) {

        Page<EduTeacher> teacherPage = new Page<>(current, limit);
        Map<String, Object> map = teacherService.getTeacherList(teacherPage);
        return ResultResponse.ok().data(map);
    }

    // 根据teacherId查找教师信息，以及该教师讲的课程
    @GetMapping("/getTeacherDetail/{teacherId}")
    public ResultResponse getTeacherDetail(@PathVariable String teacherId) {

        // 根据teacherId查找教师信息
        EduTeacher teacherInfo = teacherService.getById(teacherId);

        // 根据teacherId查找教师信息,查找所讲授的课程
        QueryWrapper<EduCourse> courseWrapper = new QueryWrapper<>();
        courseWrapper.eq("teacher_id", teacherId);
        List<EduCourse> courseList = courseService.list(courseWrapper);
        return ResultResponse.ok().data("teacherInfo", teacherInfo).data("courseList", courseList);
    }
}
