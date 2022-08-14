package com.atguigu.server_edu.controller.front;


import com.atguigu.commonutils.ResultResponse;
import com.atguigu.server_edu.entity.EduCourse;
import com.atguigu.server_edu.entity.EduTeacher;
import com.atguigu.server_edu.service.EduCourseService;
import com.atguigu.server_edu.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eduservice/indexfront")
@CrossOrigin
public class IndexFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduTeacherService teacherService;

    // 查询前八条课程数据，前四条讲师数据
    @GetMapping("/index")
    public ResultResponse index() {

        // 查前八条课程
        List<EduCourse> courseList = courseService.queryEightCourse();
       // List<EduCourse> courseList = courseService.list(courseWrapper);

        // 查四条教师
        List<EduTeacher> teacherList = teacherService.queryFourTeacher();
        //List<EduTeacher> teacherList = teacherService.list(teacherWrapper);

        return ResultResponse.ok().data("courseList", courseList).data("teacherList", teacherList);
    }
}
